package org.opennms.nutanix.pollers.entity;

import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.pollers.NutanixAbstractPoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public abstract class NutanixEntityAbstractPoller extends NutanixAbstractPoller {
    private static final Logger LOG = LoggerFactory.getLogger(NutanixEntityAbstractPoller.class);
    protected NutanixEntityAbstractPoller(final ClientManager clientManager) {
        super(clientManager);
    }
    protected abstract PollerResult poll(final Entity  entity) throws NutanixApiException;

    @Override
    public CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException {
        final var uuid = context.getNutanixUuid();
        final var type = context.getNutanixEntityType();

        Entity entity;

        switch (type) {
            case Cluster:
                entity = context.client().getCluster(uuid);
                break;
            case Host:
                entity = context.client().getHost(uuid);
                break;
            case VirtualMachine:
                entity = context.client().getVM(uuid);
                break;
            default:
                LOG.info("poll: No Entity with uuid/type {}/{}", uuid, type);
                return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                        .setStatus(Status.Down)
                        .setReason("No Entity with uuid/type:" + uuid + "/" + type )
                        .build());


        }
        return CompletableFuture.completedFuture(this.poll(entity));
    }

}
