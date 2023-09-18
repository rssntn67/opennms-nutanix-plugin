package org.opennms.nutanix.pollers.cluster.status;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.cluster.NutanixClusterAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixClusterStatusPoller extends NutanixClusterAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixClusterStatusPoller.class);

    public NutanixClusterStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Cluster cluster) {
        LOG.info("poll: Cluster isAvailable {}", cluster.isAvailable);
        if (!cluster.isAvailable) {
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("Cluster not available")
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .setStatus(Status.Up)
                                    .build();
    }

}
