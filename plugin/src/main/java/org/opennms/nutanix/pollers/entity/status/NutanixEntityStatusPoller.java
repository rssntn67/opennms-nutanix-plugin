package org.opennms.nutanix.pollers.entity.status;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.entity.NutanixEntityAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixEntityStatusPoller extends NutanixEntityAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixEntityStatusPoller.class);

    public NutanixEntityStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    @Override
    protected PollerResult poll(Entity entity) {
        LOG.info("poll: Entity state: {}", entity.state);
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
