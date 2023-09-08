package org.opennms.nutanix.pollers.cluster.service;

import java.util.Objects;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.cluster.AbstractClusterPoller;

public class ClusterServiceStatusPoller extends AbstractClusterPoller {
    public ClusterServiceStatusPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Cluster cluster) throws NutanixApiException {
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
