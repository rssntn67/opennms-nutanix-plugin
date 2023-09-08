package org.opennms.nutanix.pollers.vm.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;

public class NutanixVMStatusPollerFactory extends NutanixAbstractPoller.Factory<NutanixVMStatusPoller> {

    public NutanixVMStatusPollerFactory(final ClientManager clientManager,
                                        final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixVMStatusPoller.class);
    }

    @Override
    protected NutanixVMStatusPoller createPoller(final ClientManager clientManager) {
        return new NutanixVMStatusPoller(clientManager);
    }
}