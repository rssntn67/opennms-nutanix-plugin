package org.opennms.nutanix.pollers.cluster;

import java.util.Objects;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;

public class ClusterServiceStatusPoller extends AbstractClusterStatusPoller {
    public ClusterServiceStatusPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Cluster gateway) throws NutanixApiException {
        if (!Objects.equals(gateway.operationMode, "NORMAL")) {
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("Cluster out of service")
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .setStatus(Status.Up)
                                    .build();
    }

}
