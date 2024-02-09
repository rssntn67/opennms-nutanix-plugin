package org.opennms.nutanix.pollers;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixClusterPollerFactory extends NutanixClusterAbstractPoller.Factory<NutanixClusterPoller> {

    public NutanixClusterPollerFactory(final ConnectionManager connectionManager, final ClientManager clientManager) {
        super(connectionManager,clientManager, NutanixClusterPoller.class);
    }

    @Override
    protected NutanixClusterPoller createPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        return new NutanixClusterPoller(connectionManager, clientManager);
    }
}
