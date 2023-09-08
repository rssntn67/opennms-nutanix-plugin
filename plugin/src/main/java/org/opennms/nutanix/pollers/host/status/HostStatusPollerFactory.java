package org.opennms.nutanix.pollers.host.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.host.AbstractHostPoller;

public class HostStatusPollerFactory extends AbstractHostPoller.Factory<HostStatusPoller> {

    public HostStatusPollerFactory(final ClientManager clientManager,
                                   final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, HostStatusPoller.class);
    }

    @Override
    protected HostStatusPoller createPoller(final ClientManager clientManager) {
        return new HostStatusPoller(clientManager);
    }
}
