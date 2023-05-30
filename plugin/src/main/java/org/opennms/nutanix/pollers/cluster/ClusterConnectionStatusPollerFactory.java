package org.opennms.nutanix.pollers.cluster;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class ClusterConnectionStatusPollerFactory extends AbstractClusterStatusPoller.Factory<ClusterConnectionStatusPoller> {

    public ClusterConnectionStatusPollerFactory(final ClientManager clientManager,
                                                final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, ClusterConnectionStatusPoller.class);
    }

    @Override
    protected ClusterConnectionStatusPoller createPoller(final ClientManager clientManager) {
        return new ClusterConnectionStatusPoller(clientManager);
    }
}
