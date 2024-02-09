package org.opennms.nutanix.pollers;

import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NutanixVMAbstractPoller extends NutanixAbstractPoller {
    protected NutanixVMAbstractPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        super(connectionManager,clientManager);
    }

    private static final Logger LOG = LoggerFactory.getLogger(NutanixVMAbstractPoller.class);

    protected abstract PollerResult poll(final VM vm) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();
        final var type = context.getNutanixEntityType();
        if (type != Entity.EntityType.VirtualMachine) {
            LOG.info("poll: EntityType is: {}", type);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                    .setStatus(Status.Unknown)
                    .setReason("No VM Entity: " + type)
                    .build());
        }

        final var vm = context.client().getVM(uuid);

        if (vm == null) {
            LOG.info("poll: no VM with uuid: {}", uuid);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Unknown)
                                                                          .setReason("No Nutanix VM found with uuid: " + uuid)
                                                                          .build());
        }

        return CompletableFuture.completedFuture(this.poll(vm));
    }
}
