package org.opennms.nutanix.events;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.dao.NodeDao;
import org.opennms.integration.api.v1.events.EventForwarder;
import org.opennms.integration.api.v1.health.Context;
import org.opennms.integration.api.v1.health.HealthCheck;
import org.opennms.integration.api.v1.health.Response;
import org.opennms.integration.api.v1.health.Status;
import org.opennms.integration.api.v1.health.immutables.ImmutableResponse;
import org.opennms.integration.api.v1.model.MetaData;
import org.opennms.integration.api.v1.model.Node;
import org.opennms.integration.api.v1.model.Severity;
import org.opennms.integration.api.v1.model.immutables.ImmutableEventParameter;
import org.opennms.integration.api.v1.model.immutables.ImmutableInMemoryEvent;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.requisition.NutanixRequisitionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

public class NutanixEventIngestor implements Runnable, HealthCheck {
    private static final Logger LOG = LoggerFactory.getLogger(NutanixEventIngestor.class);

    public static final String NUTANIX_ALARM_UEI = "uei.opennms.org/plugin/nutanix/alert";
    public static final String NUTANIX_ALARM_RESOLVED_UEI = "uei.opennms.org/plugin/nutanix/alertResolved";

    private final Set<String> processedAlarm = new HashSet<>();
    private final Set<String> processedAlarmResolved = new HashSet<>();

    private final static Map<String, Severity> SEVERITY_MAP = ImmutableMap.<String, Severity>builder()
            .put("critical", Severity.MAJOR)
            .put("warning", Severity.MINOR)
            .put("informational", Severity.WARNING)
            .put("audit", Severity.NORMAL)
            .build();

    private final ConnectionManager connectionManager;

    private final NodeDao nodeDao;

    private final EventForwarder eventForwarder;

    private Instant lastPoll = null;

    private ScheduledFuture<?> scheduledFuture;

    private final long delay;

    private static class RequisitionIdentifier {
        private final String foreignSource;
        private final String uuid;
        private final String alias;

        private final Entity.EntityType type;

        public RequisitionIdentifier(final Node n) {
            final Map<String, String> map = n.getMetaData().stream()
                    .filter(metaData -> Objects.equals(metaData.getContext(), NutanixRequisitionProvider.NUTANIX_METADATA_CONTEXT))
                    .collect(Collectors.toMap(MetaData::getKey, MetaData::getValue));
            foreignSource = Objects.requireNonNull(n.getForeignSource());
            uuid = Objects.requireNonNull(map.get("uuid"));
            alias = Objects.requireNonNull(map.get("alias"));
            type = Objects.requireNonNull(Entity.EntityType.valueOf(map.get("type")));
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            RequisitionIdentifier requisitionIdentifier = (RequisitionIdentifier) o;
            return Objects.equals(foreignSource, requisitionIdentifier.foreignSource)
                    && Objects.equals(uuid, requisitionIdentifier.uuid)
                    && Objects.equals(type, requisitionIdentifier.type)
                    && Objects.equals(alias, requisitionIdentifier.alias);
        }

        @Override
        public int hashCode() {
            return Objects.hash(foreignSource, uuid, type, alias);
        }
    }

    public NutanixEventIngestor(final ConnectionManager connectionManager,
                                final NodeDao nodeDao,
                                final EventForwarder eventForwarder,
                                final long delay) {
        this.connectionManager = Objects.requireNonNull(connectionManager);
        this.nodeDao = Objects.requireNonNull(nodeDao);
        this.eventForwarder = Objects.requireNonNull(eventForwarder);
        this.delay = delay;

        LOG.debug("Nutanix Event Ingestor is initializing (delay = {}ms).", delay);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(this, this.delay, this.delay, TimeUnit.MILLISECONDS);
    }

    public void destroy() {
        LOG.debug("Nutanix Event Ingestor is shutting down.");
        scheduledFuture.cancel(false);
        scheduledFuture = null;
    }

    @Override
    public void run() {
        LOG.debug("Nutanix Event Ingestor is polling for events.");

        final Instant now = Instant.now();

        if (lastPoll == null) {
            lastPoll = now.minus(delay, ChronoUnit.MILLIS);
        }

        final Set<RequisitionIdentifier> requisitionIdentifiers = nodeDao.getNodes().stream()
                .filter(node -> node.getMetaData().stream()
                    .anyMatch(metaData -> Objects.equals(NutanixRequisitionProvider.NUTANIX_METADATA_CONTEXT, metaData.getContext())))
                .map(RequisitionIdentifier::new)
                .collect(Collectors.toSet());

        Map<String, Set<RequisitionIdentifier>> uuidRequisitionIdentifierMap = new HashMap<>();
        for (RequisitionIdentifier ri : requisitionIdentifiers) {
            if (!uuidRequisitionIdentifierMap.containsKey(ri.uuid)) {
                uuidRequisitionIdentifierMap.put(ri.uuid, new HashSet<>());
            }
            uuidRequisitionIdentifierMap.get(ri.uuid).add(ri);
        }

        for(final String alias : requisitionIdentifiers.stream().map(ri -> ri.alias).collect(Collectors.toSet())) {

            try {
                final Optional<ApiClientService> nutanixApiClientService = connectionManager.getClient(alias);

                if (nutanixApiClientService.isPresent()) {
                    try {
                        processAlerts(lastPoll, now, nutanixApiClientService.get(), uuidRequisitionIdentifierMap);
                    } catch (NutanixApiException e) {
                        LOG.error("Cannot process alarms for alias='{}'", alias);
                    }
                }
            } catch (NutanixApiException e) {
                LOG.debug("Cannot create customer client for alias='{}'", alias);
            }
        }

        // interval start and end is inclusive
        lastPoll = now.plus(1, ChronoUnit.MILLIS);
    }

    private void processAlert(final Alert alert, final String uei, final Map<String, Set<RequisitionIdentifier>> uiidMap) {
        alert.affectedEntities.forEach( entity -> {
            if (uiidMap.containsKey(entity.uuid)) {
                uiidMap.get(entity.uuid).forEach(ri -> processAlertEntity(ri, alert,entity, uei));
            }
        });

    }
    private void processAlertEntity(final RequisitionIdentifier ri, final Alert alert, final Entity entity, final String uei) {
        final Node node = nodeDao.getNodeByCriteria(ri.foreignSource + ":" + ri.uuid);

        if (node == null) {
            LOG.warn("Ignoring proxy event #{} since node {} cannot be found.", alert.getClass(), ri.foreignSource + ":" + ri.uuid);
            return;
        }

        final ImmutableInMemoryEvent.Builder builder = ImmutableInMemoryEvent.newBuilder()
                .setUei(uei)
                .setSource(NutanixEventIngestor.class.getCanonicalName())
                .setNodeId(node.getId())
                .setSeverity(SEVERITY_MAP.get(alert.severity))
                .setInterface(null);

        builder.addParameter(ImmutableEventParameter.newInstance("msg", alert.message));
        builder.addParameter(ImmutableEventParameter.newInstance("reductionKey", alert.uuid));
        builder.addParameter(ImmutableEventParameter.newInstance("descr", alert.descr));
        builder.addParameter(ImmutableEventParameter.newInstance("severity", alert.severity));
        builder.addParameter(ImmutableEventParameter.newInstance("alertType", alert.alertType));
        builder.addParameter(ImmutableEventParameter.newInstance("alertUuid", alert.uuid));
        builder.addParameter(ImmutableEventParameter.newInstance("entityType", String.valueOf(entity.type)));
        builder.addParameter(ImmutableEventParameter.newInstance("entityUuid", entity.uuid));
        builder.addParameter(ImmutableEventParameter.newInstance("entityName", entity.name));
        builder.setTime(Date.from(alert.creationTime.toInstant()));

        eventForwarder.sendSync(builder.build());
    }

    private void processAlerts(final Instant start, final Instant end, final ApiClientService client, Map<String, Set<RequisitionIdentifier>> uuidMap) throws NutanixApiException {
        final List<Alert> alerts = client.getAlerts();
        LOG.debug("{} alert found.", alerts.size());

        int processed = 0;
        int resolved = 0;
        int ignored = 0;
        for (final Alert alert : alerts) {
            if (alert.isResolved && !processedAlarmResolved.contains(alert.uuid)) {
                processedAlarmResolved.add(alert.uuid);
                processAlert(alert, NUTANIX_ALARM_RESOLVED_UEI, uuidMap);
                resolved++;
            } else if (!alert.isResolved && !processedAlarm.contains(alert.uuid)){
                processedAlarm.add(alert.uuid);
                processAlert(alert, NUTANIX_ALARM_UEI, uuidMap);
                processed++;
            } else {
                ignored++;
            }
        }
        LOG.debug("{} alert raised, {} alert resolved, {} events ignored.", processed, resolved, ignored);
    }
    @Override
    public String getDescription() {
        return "Nutanix Event Ingestor";
    }

    @Override
    public Response perform(Context context) {
        return ImmutableResponse.newBuilder()
                .setStatus(scheduledFuture.isDone() ? Status.Failure : Status.Success)
                .setMessage(scheduledFuture.isDone() ? "Not running" : "Running")
                .build();
    }
}
