package org.opennms.nutanix.pollers.vm;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;

public class VMStatusPoller extends AbstractVMStatusPoller {
    public VMStatusPoller(ClientManager clientManager) {
        super(clientManager);
    }

    protected PollerResult poll(final VM vm) {
        if (!vm.powerState.equalsIgnoreCase("ON")) {
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
