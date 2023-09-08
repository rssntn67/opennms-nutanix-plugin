package org.opennms.nutanix.pollers.entity.status;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.entity.AbstractEntityPoller;

public class EntityStatusPollerFactory extends AbstractEntityPoller.Factory<EntityStatusPoller> {

    public EntityStatusPollerFactory(final ClientManager clientManager,
                                     final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, EntityStatusPoller.class);
    }

    @Override
    protected EntityStatusPoller createPoller(final ClientManager clientManager) {
        return new EntityStatusPoller(clientManager);
    }
}
