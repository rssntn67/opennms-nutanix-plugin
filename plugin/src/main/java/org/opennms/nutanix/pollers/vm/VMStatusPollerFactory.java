package org.opennms.nutanix.pollers.vm;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class VMStatusPollerFactory extends AbstractVMStatusPoller.Factory<VMStatusPoller> {

    public VMStatusPollerFactory(final ClientManager clientManager,
                                 final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, VMStatusPoller.class);
    }

    @Override
    protected VMStatusPoller createPoller(final ClientManager clientManager) {
        return new VMStatusPoller(clientManager);
    }
}
