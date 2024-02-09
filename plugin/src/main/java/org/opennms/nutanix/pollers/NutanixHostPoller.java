package org.opennms.nutanix.pollers;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixHostPoller extends NutanixHostAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixHostPoller.class);

    public NutanixHostPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        super(connectionManager,clientManager);
    }

    @Override
    protected PollerResult poll(final Host host) {
        if (!host.hostType.equalsIgnoreCase("HYPER_CONVERGED")) {
            LOG.info("poll: host.hostType {}", host.hostType);
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("Host not available")
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .setStatus(Status.Up)
                                    .build();
    }

}
