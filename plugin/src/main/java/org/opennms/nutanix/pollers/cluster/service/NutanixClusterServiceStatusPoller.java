package org.opennms.nutanix.pollers.cluster.service;

import java.util.Objects;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.cluster.NutanixClusterAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixClusterServiceStatusPoller extends NutanixClusterAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixClusterServiceStatusPoller.class);

    public NutanixClusterServiceStatusPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Cluster cluster) throws NutanixApiException {
        LOG.info("poll: Cluster Operation Mode: {}", cluster.operationMode);
        if (!Objects.equals(cluster.operationMode, "NORMAL")) {
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("Cluster Operation Mode: " + cluster.operationMode)
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .setStatus(Status.Up)
                                    .build();
    }

}
