package org.opennms.nutanix.pollers.host;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.internal.Utils;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;

public abstract class NutanixHostAbstractPoller extends NutanixAbstractPoller {
    public static final String ATTR_CLUSTER_UUID = "uuid";

    protected NutanixHostAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected abstract PollerResult poll(final Host host) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();

        final var host = context.client().getHost(uuid);

        if (host == null) {
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No Nutanix Host found with uuid: " + uuid)
                                                                          .build());
        }
        if (host.hypervisorIp == null) {
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("No Hypervisor Ip found for Host with uuid: " + uuid)
                    .build());
        }
        if (!Objects.equals(Utils.getValidInetAddress(host.hypervisorIp), context.request.getAddress())) {
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("No Match for Hypervisor Ip Found for Host with uuid: " + uuid)
                    .build());
        }

        return CompletableFuture.completedFuture(this.poll(host));
    }
 }
