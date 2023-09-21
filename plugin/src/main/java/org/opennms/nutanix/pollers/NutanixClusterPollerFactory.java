package org.opennms.nutanix.pollers;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixClusterPollerFactory extends NutanixClusterAbstractPoller.Factory<NutanixClusterPoller> {

    public NutanixClusterPollerFactory(final ClientManager clientManager,
                                       final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixClusterPoller.class);
    }

    @Override
    protected NutanixClusterPoller createPoller(final ClientManager clientManager) {
        return new NutanixClusterPoller(clientManager);
    }
}
