package org.opennms.nutanix.pollers.cluster.service;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.cluster.AbstractClusterPoller;

public class ClusterServiceStatusPollerFactory extends AbstractClusterPoller.Factory<ClusterServiceStatusPoller> {

    public ClusterServiceStatusPollerFactory(final ClientManager clientManager,
                                             final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, ClusterServiceStatusPoller.class);
    }

    @Override
    protected ClusterServiceStatusPoller createPoller(final ClientManager clientManager) {
        return new ClusterServiceStatusPoller(clientManager);
    }
}
