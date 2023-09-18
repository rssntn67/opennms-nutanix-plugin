package org.opennms.nutanix.pollers.vm;

import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NutanixVMAbstractPoller extends NutanixAbstractPoller {
    protected NutanixVMAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    private static final Logger LOG = LoggerFactory.getLogger(NutanixVMAbstractPoller.class);

    protected abstract PollerResult poll(final VM vm) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();
        final var vm = context.client().getVM(uuid);

        if (vm == null) {
            LOG.info("poll: no VM with uuid: {}", uuid);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No Nutanix VM found with uuid: " + uuid)
                                                                          .build());
        }

        return CompletableFuture.completedFuture(this.poll(vm));
    }
}
