package org.opennms.nutanix.pollers;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixHostPollerFactory extends NutanixHostAbstractPoller.Factory<NutanixHostPoller> {

    public NutanixHostPollerFactory(final ConnectionManager connectionManager,
                                    final ClientManager clientManager) {
        super(connectionManager, clientManager, NutanixHostPoller.class);
    }

    @Override
    protected NutanixHostPoller createPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        return new NutanixHostPoller(connectionManager, clientManager);
    }
}
