package org.opennms.nutanix.pollers;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixVMPollerFactory extends NutanixAbstractPoller.Factory<NutanixVMPoller> {

    public NutanixVMPollerFactory(final ClientManager clientManager,
                                  final ConnectionManager connectionManager) {
        super(connectionManager, clientManager, NutanixVMPoller.class);
    }

    @Override
    protected NutanixVMPoller createPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        return new NutanixVMPoller(connectionManager, clientManager);
    }
}
