package org.opennms.nutanix.pollers.cluster.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.cluster.NutanixClusterAbstractPoller;

public class NutanixClusterStatusPollerFactory extends NutanixClusterAbstractPoller.Factory<NutanixClusterStatusPoller> {

    public NutanixClusterStatusPollerFactory(final ClientManager clientManager,
                                             final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixClusterStatusPoller.class);
    }

    @Override
    protected NutanixClusterStatusPoller createPoller(final ClientManager clientManager) {
        return new NutanixClusterStatusPoller(clientManager);
    }
}
