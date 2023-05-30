package org.opennms.nutanix.pollers.cluster;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.AbstractStatusPoller;

public abstract class AbstractClusterStatusPoller extends AbstractStatusPoller {
    public static final String ATTR_CLUSTER_UUID = "uuid";

    protected AbstractClusterStatusPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected abstract PollerResult poll(final Cluster gateway) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = Objects.requireNonNull(context.getPollerAttributes().get(ATTR_CLUSTER_UUID),
                                                     "Missing attribute: " + ATTR_CLUSTER_UUID);

        final var cluster = context.client().getCluster(uuid);

        if (cluster == null) {
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No cluster with id " + uuid)
                                                                          .build());
        }

        return CompletableFuture.completedFuture(this.poll(cluster));
    }

    public static abstract class Factory<T extends AbstractClusterStatusPoller> extends AbstractStatusPoller.Factory<T> {

        protected Factory(final ClientManager clientManager,
                          final ConnectionManager connectionManager,
                          final Class<T> clazz) {
            super(clientManager, connectionManager, clazz);
        }
    }
}
