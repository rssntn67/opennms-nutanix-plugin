package org.opennms.nutanix.pollers;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixVMPollerFactory extends NutanixAbstractPoller.Factory<NutanixVMPoller> {

    public NutanixVMPollerFactory(final ClientManager clientManager,
                                  final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixVMPoller.class);
    }

    @Override
    protected NutanixVMPoller createPoller(final ClientManager clientManager) {
        return new NutanixVMPoller(clientManager);
    }
}
