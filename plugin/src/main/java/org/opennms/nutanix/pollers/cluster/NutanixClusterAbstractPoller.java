package org.opennms.nutanix.pollers.cluster;

import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NutanixClusterAbstractPoller extends NutanixAbstractPoller {
    private static final Logger LOG = LoggerFactory.getLogger(NutanixClusterAbstractPoller.class);

    protected NutanixClusterAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected abstract PollerResult poll(final Cluster cluster) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();
        final var cluster = context.client().getCluster(uuid);

        if (cluster == null) {
            LOG.info("poll: no cluster with uuid: {}", uuid);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No cluster with uuid " + uuid)
                                                                          .build());
        }

        return CompletableFuture.completedFuture(this.poll(cluster));
    }
}
