package org.opennms.nutanix.pollers.entity.status;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.entity.NutanixEntityAbstractPoller;

public class NutanixEntityStatusPoller extends NutanixEntityAbstractPoller {
    public NutanixEntityStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    @Override
    protected PollerResult poll(Entity entity) {
        if (!entity.state.equalsIgnoreCase("COMPLETE")) {
            return ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("Entity state: " + entity.state)
                    .build();
        }

        return ImmutablePollerResult.newBuilder()
                .setStatus(Status.Up)
                .build();    }

}
