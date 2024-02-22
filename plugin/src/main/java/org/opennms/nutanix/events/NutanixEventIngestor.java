package org.opennms.nutanix.events;

import java.time.Instant;
import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.config.events.AlarmType;
import org.opennms.integration.api.v1.dao.AlarmDao;
import org.opennms.integration.api.v1.dao.NodeDao;
import org.opennms.integration.api.v1.events.EventForwarder;
import org.opennms.integration.api.v1.health.Context;
import org.opennms.integration.api.v1.health.HealthCheck;
import org.opennms.integration.api.v1.health.Response;
import org.opennms.integration.api.v1.health.Status;
import org.opennms.integration.api.v1.health.immutables.ImmutableResponse;
import org.opennms.integration.api.v1.model.Alarm;
import org.opennms.integration.api.v1.model.MetaData;
import org.opennms.integration.api.v1.model.Node;
import org.opennms.integration.api.v1.model.Severity;
import org.opennms.integration.api.v1.model.immutables.ImmutableEventParameter;
import org.opennms.integration.api.v1.model.immutables.ImmutableInMemoryEvent;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.requisition.NutanixRequisitionProvider;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

public class NutanixEventIngestor implements Runnable, HealthCheck {
    private static final Logger LOG = LoggerFactory.getLogger(NutanixEventIngestor.class);

    public static final String NUTANIX_ALARM_UEI = "uei.opennms.org/plugin/nutanix/alert";
    public static final String NUTANIX_ALARM_RESOLVED_UEI = "uei.opennms.org/plugin/nutanix/alertResolved";

    private final static Map<String, Severity> SEVERITY_MAP = ImmutableMap.<String, Severity>builder()
            .put("critical", Severity.MAJOR)
            .put("warning", Severity.MINOR)
            .put("informational", Severity.WARNING)
            .put("audit", Severity.NORMAL)
            .build();

    private final ConnectionManager connectionManager;
    private final ClientManager clientManager;

    private final NodeDao nodeDao;
    private final AlarmDao alarmDao;

    private final EventForwarder eventForwarder;

    private Instant lastPoll = null;

    private ScheduledFuture<?> scheduledFuture;

    private final long delay;

    private final int retrieve_days;

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
                                final ClientManager clientManager,
                                final NodeDao nodeDao,
                                final AlarmDao alarmDao,
                                final EventForwarder eventForwarder,
                                final long delay,
                                final int retrieve_days) {
        this.connectionManager = Objects.requireNonNull(connectionManager);
        this.clientManager = Objects.requireNonNull(clientManager);
        this.nodeDao = Objects.requireNonNull(nodeDao);
        this.alarmDao = Objects.requireNonNull(alarmDao);
        this.eventForwarder = Objects.requireNonNull(eventForwarder);
        this.delay = delay;
        this.retrieve_days = retrieve_days;

        LOG.debug("Nutanix Event Ingestor is initializing (delay = {}ms).", delay);
        LOG.debug("Nutanix Event Ingestor is initializing (days = {}ms).", delay);
        ScheduledExecutorService scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        scheduledFuture = scheduledExecutorService.scheduleWithFixedDelay(this, this.delay, this.delay, TimeUnit.MILLISECONDS);
    }

    public void destroy() {
        LOG.debug("Nutanix Event Ingestor is shutting down.");
        scheduledFuture.cancel(false);
        scheduledFuture = null;
    }

    private ApiClientService client(String alias) throws NutanixApiException {
        var connection =  connectionManager.getConnection(alias);
        if (connection.isEmpty()) {
            throw new NutanixApiException("No connection for alias", new NullPointerException("No connection found for "+ alias));
        }
        if (clientManager.validate(connection.get()).isEmpty()) {
            LOG.info("Using Default Connection Alias {}", connection.get().getAlias());
            return clientManager.getClient(connection.get());
        }
        if (Strings.isNullOrEmpty(connection.get().getConnectionPool())) {
            throw new NutanixApiException("Connection for alias is not valid and no pool available", new NullPointerException("Connection is not valid for " + alias));
        }
        for (var poolMemberConnection: connectionManager.getConnectionPool(connection.get().getConnectionPool()) ) {
            if (poolMemberConnection.isPresent()) {
                if (clientManager.validate(poolMemberConnection.get()).isEmpty()) {
                    LOG.info("Using Pooled Connection {} with Alias {}",
                            poolMemberConnection.get().getConnectionPool(),
                            poolMemberConnection.get().getAlias());
                    return clientManager.getClient(poolMemberConnection.get());
                }
            }
        }
        throw new NutanixApiException("Connection for alias is not valid and no pool available", new NullPointerException("Connection is not valid for " + alias));
    }

    @Override
    public void run() {
        LOG.debug("run: getting events...");

        final Instant now = Instant.now();

        if (lastPoll == null) {
            lastPoll = now.minus(delay, ChronoUnit.MILLIS);
        }

        final Set<RequisitionIdentifier> requisitionIdentifiers = nodeDao.getNodes().stream()
                .filter(node -> node.getMetaData().stream()
                    .anyMatch(metaData -> Objects.equals(NutanixRequisitionProvider.NUTANIX_METADATA_CONTEXT, metaData.getContext())))
                .map(RequisitionIdentifier::new)
                .collect(Collectors.toSet());

        Map<String, Set<RequisitionIdentifier>> uuidMap = new HashMap<>();
        for (RequisitionIdentifier ri : requisitionIdentifiers) {
            if (!uuidMap.containsKey(ri.uuid)) {
                uuidMap.put(ri.uuid, new HashSet<>());
            }
            uuidMap.get(ri.uuid).add(ri);
        }

        Map<String, AlarmType> ntxAlarms =
                alarmDao.getAlarms().stream()
                        .filter(a -> a.getReductionKey().equals(NUTANIX_ALARM_UEI))
                        .collect(Collectors.toMap(a->a.getReductionKey().substring(a.getReductionKey().lastIndexOf(":")+1),
                                Alarm::getType));
        LOG.debug("run:  nutanix alarm {}", ntxAlarms);
        LOG.info("run: found {} nutanix alarm", ntxAlarms.size());
        for(final String alias : requisitionIdentifiers.stream().map(ri -> ri.alias).collect(Collectors.toSet())) {
            try {
                LOG.info("run: process alert for alias: {}", alias);
                processAlerts(client(alias).getAlerts(), uuidMap, ntxAlarms);
            } catch (NutanixApiException e) {
                LOG.error("Cannot process alarms for alias='{}'. {}", alias, e.getMessage(),e);
            }
        }

        // interval start and end is inclusive
        lastPoll = now.plus(1, ChronoUnit.MILLIS);
        LOG.debug("run: events got");
    }

    private void processAlerts(final List<Alert> alerts, final Map<String, Set<RequisitionIdentifier>> uuidMap, Map<String,AlarmType> ntxAlarms) {
        int processed = 0;
        int resolved = 0;
        int ignored = 0;
        assert alerts != null;
        OffsetDateTime before = OffsetDateTime.now().minusDays(retrieve_days);

        for (final Alert alert : alerts) {
            if (alert.creationTime.isBefore(before)) {
                continue;
            }
            if (alert.isResolved && ntxAlarms.containsKey(alert.uuid) && ntxAlarms.get(alert.uuid).equals(AlarmType.PROBLEM)) {
                processAlert(alert, NUTANIX_ALARM_RESOLVED_UEI, uuidMap);
                resolved++;
            } else if (!alert.isResolved && !ntxAlarms.containsKey(alert.uuid)){
                processAlert(alert, NUTANIX_ALARM_UEI, uuidMap);
                processed++;
            } else if (!alert.isResolved && ntxAlarms.containsKey(alert.uuid) && ntxAlarms.get(alert.uuid).equals(AlarmType.RESOLUTION)){
                processAlert(alert, NUTANIX_ALARM_UEI, uuidMap);
                processed++;
            } else {
                ignored++;
            }
        }
        LOG.info("{} event raised, {} event resolved, {} events ignored.", processed, resolved, ignored);

    }
    private void processAlert(final Alert alert, final String uei, final Map<String, Set<RequisitionIdentifier>> uuidMap) {
        alert.affectedEntities.forEach( entity -> {
            if (uuidMap.containsKey(entity.uuid)) {
                uuidMap.get(entity.uuid).forEach(ri -> processAlertEntity(ri, alert,entity, uei));
            }
        });

    }
    private void processAlertEntity(final RequisitionIdentifier ri, final Alert alert, final Entity entity, final String uei) {
        final Node node = nodeDao.getNodeByCriteria(ri.foreignSource + ":" + ri.uuid);

        if (node == null) {
            LOG.warn("Ignoring proxy event #{} since node {} cannot be found.", alert.getClass(), ri.foreignSource + ":" + ri.uuid);
            return;
        }

        Severity severity = SEVERITY_MAP.get(alert.severity);
        if (alert.isResolved) {
            severity = Severity.NORMAL;
        }
        final ImmutableInMemoryEvent.Builder builder = ImmutableInMemoryEvent.newBuilder()
                .setUei(uei)
                .setSource(NutanixEventIngestor.class.getCanonicalName())
                .setNodeId(node.getId())
                .setSeverity(severity)
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
