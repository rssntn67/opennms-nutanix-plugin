package org.opennms.nutanix.pollers.host.status;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.host.NutanixHostAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixHostStatusPoller extends NutanixHostAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixHostStatusPoller.class);

    public NutanixHostStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Host host) {
        LOG.info("poll: host.hostType {}", host.hostType);
        if (!host.hostType.equalsIgnoreCase("HYPER_CONVERGED")) {

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
