package org.opennms.nutanix.pollers.vm.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.vm.AbstractVMPoller;

public class VMStatusPollerFactory extends AbstractVMPoller.Factory<VMStatusPoller> {

    public VMStatusPollerFactory(final ClientManager clientManager,
                                 final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, VMStatusPoller.class);
    }

    @Override
    protected VMStatusPoller createPoller(final ClientManager clientManager) {
        return new VMStatusPoller(clientManager);
    }
}
