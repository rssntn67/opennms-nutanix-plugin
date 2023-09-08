package org.opennms.nutanix.pollers.cluster;

import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;

public abstract class NutanixClusterAbstractPoller extends NutanixAbstractPoller {
    public static final String ATTR_CLUSTER_UUID = "uuid";

    protected NutanixClusterAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected abstract PollerResult poll(final Cluster gateway) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();
        final var cluster = context.client().getCluster(uuid);

        if (cluster == null) {
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No cluster with id " + uuid)
                                                                          .build());
        }

        return CompletableFuture.completedFuture(this.poll(cluster));
    }
}
