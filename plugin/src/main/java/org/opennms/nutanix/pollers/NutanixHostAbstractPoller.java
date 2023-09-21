package org.opennms.nutanix.pollers;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;
import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.internal.Utils;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.clients.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NutanixHostAbstractPoller extends NutanixAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixHostAbstractPoller.class);

    protected NutanixHostAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected abstract PollerResult poll(final Host host) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();
        final var type = context.getNutanixEntityType();
        if (type != Entity.EntityType.Host) {
            LOG.info("poll: EntityType is: {}", type);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("No Host Entity: " + type)
                    .build());
        }

        final var host = context.client().getHost(uuid);

        if (host == null) {
            LOG.info("poll: no host with uuid: {}", uuid);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No Nutanix Host found with uuid: " + uuid)
                                                                          .build());
        }
        if (host.hypervisorIp == null) {
            LOG.info("poll: no hypervisorIp for host with uuid: {}", uuid);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("No Hypervisor Ip found for Host with uuid: " + uuid)
                    .build());
        }
        if (!Objects.equals(Utils.getValidInetAddress(host.hypervisorIp), context.request.getAddress())) {
            LOG.info("poll: no valid hypervisorIp {} for host with uuid: {}",host.hypervisorIp,  uuid);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("No Valid for Hypervisor Ip {} Found for Host with uuid: " + uuid)
                    .build());
        }

        return CompletableFuture.completedFuture(this.poll(host));
    }
}
