package org.opennms.nutanix.pollers.entity;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public class EntityStatusPollerFactory extends AbstractEntityStatusPoller.Factory<EntityStatusPoller> {

    public EntityStatusPollerFactory(final ClientManager clientManager,
                                     final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, EntityStatusPoller.class);
    }

    @Override
    protected EntityStatusPoller createPoller(final ClientManager clientManager) {
        return new EntityStatusPoller(clientManager);
    }
}
