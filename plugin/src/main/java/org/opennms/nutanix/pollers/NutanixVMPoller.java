package org.opennms.nutanix.pollers;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixVMPoller extends NutanixVMAbstractPoller {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixVMPoller.class);
    public NutanixVMPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        super(connectionManager,clientManager);
    }

    protected PollerResult poll(final VM vm) {
        if (!vm.powerState.equalsIgnoreCase("ON")) {
            LOG.info("poll: vm.powerState {}", vm.powerState);
            return ImmutablePollerResult.newBuilder()
                                        .setStatus(Status.Down)
                                        .setReason("VM PowerState: " + vm.powerState)
                                        .build();
        }

        return ImmutablePollerResult.newBuilder()
                                    .setStatus(Status.Up)
                                    .build();
    }

}
