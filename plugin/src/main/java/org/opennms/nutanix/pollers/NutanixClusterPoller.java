package org.opennms.nutanix.pollers;

import java.util.Objects;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixClusterPoller extends NutanixClusterAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixClusterPoller.class);

    public NutanixClusterPoller(ClientManager client) {
        super(client);
    }

    @Override
    protected PollerResult poll(final Cluster cluster) {
        LOG.info("poll: Cluster isAvailable {}", cluster.isAvailable);
        LOG.info("poll: Cluster Operation Mode: {}", cluster.operationMode);
        if (!Objects.equals(cluster.operationMode, "NORMAL")) {
            return ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Down)
                    .setReason("Cluster Operation Mode: " + cluster.operationMode)
                    .build();
        }
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
