package org.opennms.nutanix.pollers.cluster.status;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.cluster.AbstractClusterStatusPoller;

public class ClusterStatusPoller extends AbstractClusterStatusPoller {
    public ClusterStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Cluster cluster) {
        if (!cluster.isAvailable) {
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("Cluster not available")
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .setStatus(Status.Up)
                                    .build();
    }

}
