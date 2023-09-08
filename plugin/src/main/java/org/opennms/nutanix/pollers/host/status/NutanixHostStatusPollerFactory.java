package org.opennms.nutanix.pollers.host.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.host.NutanixHostAbstractPoller;

public class NutanixHostStatusPollerFactory extends NutanixHostAbstractPoller.Factory<NutanixHostStatusPoller> {

    public NutanixHostStatusPollerFactory(final ClientManager clientManager,
                                          final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixHostStatusPoller.class);
    }

    @Override
    protected NutanixHostStatusPoller createPoller(final ClientManager clientManager) {
        return new NutanixHostStatusPoller(clientManager);
    }
}
