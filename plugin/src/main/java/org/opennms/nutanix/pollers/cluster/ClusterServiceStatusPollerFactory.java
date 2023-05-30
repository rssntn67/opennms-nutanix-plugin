package org.opennms.nutanix.pollers.cluster;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class ClusterServiceStatusPollerFactory extends AbstractClusterStatusPoller.Factory<ClusterServiceStatusPoller> {

    public ClusterServiceStatusPollerFactory(final ClientManager clientManager,
                                             final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, ClusterServiceStatusPoller.class);
    }

    @Override
    protected ClusterServiceStatusPoller createPoller(final ClientManager clientManager) {
        return new ClusterServiceStatusPoller(clientManager);
    }
}
