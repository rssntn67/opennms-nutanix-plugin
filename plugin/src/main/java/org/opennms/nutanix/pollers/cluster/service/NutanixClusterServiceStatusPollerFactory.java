package org.opennms.nutanix.pollers.cluster.service;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.cluster.NutanixClusterAbstractPoller;

public class NutanixClusterServiceStatusPollerFactory extends NutanixClusterAbstractPoller.Factory<NutanixClusterServiceStatusPoller> {

    public NutanixClusterServiceStatusPollerFactory(final ClientManager clientManager,
                                                    final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixClusterServiceStatusPoller.class);
    }

    @Override
    protected NutanixClusterServiceStatusPoller createPoller(final ClientManager clientManager) {
        return new NutanixClusterServiceStatusPoller(clientManager);
    }
}
