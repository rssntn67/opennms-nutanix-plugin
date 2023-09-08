package org.opennms.nutanix.pollers.cluster.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.cluster.AbstractClusterStatusPoller;

public class ClusterStatusPollerFactory extends AbstractClusterStatusPoller.Factory<ClusterStatusPoller> {

    public ClusterStatusPollerFactory(final ClientManager clientManager,
                                      final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, ClusterStatusPoller.class);
    }

    @Override
    protected ClusterStatusPoller createPoller(final ClientManager clientManager) {
        return new ClusterStatusPoller(clientManager);
    }
}
