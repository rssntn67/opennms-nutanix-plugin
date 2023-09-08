package org.opennms.nutanix.pollers.vm.status;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.AbstractStatusPoller;

public abstract class AbstractVMStatusPoller extends AbstractStatusPoller {
    public static final String ATTR_CLUSTER_UUID = "uuid";

    protected AbstractVMStatusPoller(final ClientManager clientManager) {
        super(clientManager);
    }

    protected abstract PollerResult poll(final VM vm) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = Objects.requireNonNull(context.getPollerAttributes().get(ATTR_CLUSTER_UUID),
                                                     "Missing attribute: " + ATTR_CLUSTER_UUID);

        final var vm = context.client().getVM(uuid);

        if (vm == null) {
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason("No Nutanix VM found with uuid: " + uuid)
                                                                          .build());
        }

        return CompletableFuture.completedFuture(this.poll(vm));
    }

    public static abstract class Factory<T extends AbstractVMStatusPoller> extends AbstractStatusPoller.Factory<T> {

        protected Factory(final ClientManager clientManager,
                          final ConnectionManager connectionManager,
                          final Class<T> clazz) {
            super(clientManager, connectionManager, clazz);
        }
    }
}
