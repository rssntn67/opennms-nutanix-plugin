package org.opennms.nutanix.pollers.cluster;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.clients.ClientManager;

public class ClusterConnectionStatusPoller extends AbstractClusterStatusPoller {
    public ClusterConnectionStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final Cluster cluster) throws NutanixApiException {
        if (!cluster.isAvailable) {
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("Cluster not available")
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .addProperty("operationMode", new Number() {
                                        @Override
                                        public int intValue() {
                                            return 0;
                                        }

                                        @Override
                                        public long longValue() {
                                            return 0;
                                        }

                                        @Override
                                        public float floatValue() {
                                            return 0;
                                        }

                                        @Override
                                        public double doubleValue() {
                                            return 0;
                                        }
                                    })
                                    .setStatus(Status.Up)
                                    .build();
    }

}
