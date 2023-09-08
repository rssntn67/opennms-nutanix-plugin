package org.opennms.nutanix.pollers.entity.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.entity.NutanixEntityAbstractPoller;

public class NutanixEntityStatusPollerFactory extends NutanixEntityAbstractPoller.Factory<NutanixEntityStatusPoller> {

    public NutanixEntityStatusPollerFactory(final ClientManager clientManager,
                                            final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixEntityStatusPoller.class);
    }

    @Override
    protected NutanixEntityStatusPoller createPoller(final ClientManager clientManager) {
        return new NutanixEntityStatusPoller(clientManager);
    }
}
