package org.opennms.nutanix.pollers.entity;

import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;

public abstract class NutanixEntityAbstractPoller extends NutanixAbstractPoller {
    public static final String ATTR_ENTITY_UUID = "uuid";
    public static final String ATTR_ENTITY_TYPE = "type";

    protected NutanixEntityAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }
    protected abstract PollerResult poll(final Entity  entity) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = Objects.requireNonNull(context.getPollerAttributes().get(ATTR_ENTITY_UUID),
                                                     "Missing attribute: " + ATTR_ENTITY_UUID);

        final var type = Objects.requireNonNull(context.getPollerAttributes().get(ATTR_ENTITY_TYPE),
                "Missing attribute: " + ATTR_ENTITY_TYPE);

        Entity entity;

        switch (type) {
            case "Cluster":
                entity = context.client().getCluster(uuid);
                break;
            case "Host":
                entity = context.client().getHost(uuid);
                break;
            case "VirtualMachine":
                entity = context.client().getVM(uuid);
                break;
            default:
                return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                        .setStatus(Status.Down)
                        .setReason("No Entity with uuid/type:" + uuid + "/" + type )
                        .build());


        }
        return CompletableFuture.completedFuture(this.poll(entity));
    }

    public static abstract class Factory<K extends NutanixEntityAbstractPoller> extends NutanixAbstractPoller.Factory<K> {

        protected Factory(final ClientManager clientManager,
                          final ConnectionManager connectionManager,
                          final Class<K> clazz) {
            super(clientManager, connectionManager, clazz);
        }
    }
}
