
package org.opennms.nutanix.pollers;

import static org.opennms.nutanix.connections.ConnectionManager.ALIAS_KEY;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerRequest;
import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.ServicePoller;
import org.opennms.integration.api.v1.pollers.ServicePollerFactory;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;

public abstract class NutanixAbstractPoller implements ServicePoller {
    private static final Logger LOG = LoggerFactory.getLogger(NutanixAbstractPoller.class);
    private final ClientManager clientManager;
    private final ConnectionManager connectionManager;

    private final static String UUID_KEY = "uuid";
    private final static String TYPE_KEY = "type";

    protected NutanixAbstractPoller(final ConnectionManager connectionManager, final ClientManager clientManager) {
        this.connectionManager = Objects.requireNonNull(connectionManager);
        this.clientManager = Objects.requireNonNull(clientManager);
    }

    protected abstract CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException;

    @Override
    public final CompletableFuture<PollerResult> poll(final PollerRequest pollerRequest) {
        try {
            LOG.debug("poll: {} {}", pollerRequest.getAddress().getHostAddress(), pollerRequest.getServiceName());
            return this.poll(new Context(pollerRequest));
        } catch (final NutanixApiException e) {
            LOG.warn("Nutanix prism communication failed", e);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Unknown)
                                                                          .setReason(e.getMessage())
                                                                          .build());
        }
    }

    public static abstract class Factory<T extends NutanixAbstractPoller> implements ServicePollerFactory<T> {

        private final ClientManager clientManager;
        private final ConnectionManager connectionManager;

        private final Class<T> clazz;

        protected Factory(final ConnectionManager connectionManager, final ClientManager clientManager,
                          final Class<T> clazz) {
            this.connectionManager = Objects.requireNonNull(connectionManager);
            this.clientManager = Objects.requireNonNull(clientManager);

            this.clazz = Objects.requireNonNull(clazz);
        }

        protected abstract T createPoller(ConnectionManager connectionManager, ClientManager clientManager);

        @Override
        public final T createPoller() {
            LOG.debug("Factory::createPoller -> class {}", getPollerClassName());
            return this.createPoller(this.connectionManager, this.clientManager);
        }

        @Override
        public final String getPollerClassName() {
            return this.clazz.getCanonicalName();
        }



        @Override
        public final Map<String, String> getRuntimeAttributes(final PollerRequest pollerRequest) {
            final var alias = Objects.requireNonNull(pollerRequest.getPollerAttributes().get(ALIAS_KEY), "Missing property: " + ALIAS_KEY);
            final var uuid = Objects.requireNonNull(pollerRequest.getPollerAttributes().get(UUID_KEY), "Missing property: " + UUID_KEY);
            final var type = Objects.requireNonNull(pollerRequest.getPollerAttributes().get(TYPE_KEY), "Missing property: " + TYPE_KEY);
            final var attrs = ImmutableMap.<String,String>builder();
            attrs.put(ALIAS_KEY,alias);
            attrs.put(UUID_KEY, uuid);
            attrs.put(TYPE_KEY, type);

            return attrs.build();
        }
    }

    public class Context {
        public final PollerRequest request;

        public final String alias;
        public final String type;
        public final String uuid;

        public Context(final PollerRequest request) {
            this.request = Objects.requireNonNull(request);
            this.alias = Objects.requireNonNull(this.request.getPollerAttributes().get(ALIAS_KEY),
                    "Missing attribute: " + ALIAS_KEY);
            this.uuid = Objects.requireNonNull(this.request.getPollerAttributes().get(UUID_KEY),
                    "Missing attribute: " + UUID_KEY);
            this.type = Objects.requireNonNull(this.request.getPollerAttributes().get(TYPE_KEY),
                    "Missing attribute: " + TYPE_KEY);
        }

        public String getNutanixUuid() {
            return uuid;
        }
        public Entity.EntityType getNutanixEntityType() {
            return Entity.EntityType.valueOf(type);
        }

        public ApiClientService client() throws NutanixApiException {
            var connection =  NutanixAbstractPoller.this.connectionManager.getConnection(alias);
            if (connection.isEmpty()) {
                throw new NutanixApiException("No connection for alias", new NullPointerException("No connection found for "+ alias));
            }
            if (NutanixAbstractPoller.this.clientManager.validate(connection.get()).isEmpty()) {
                LOG.info("Using Default Connection Alias {}", connection.get().getAlias());
                return NutanixAbstractPoller.this.clientManager.getClient(connection.get());
            }
            if (Strings.isNullOrEmpty(connection.get().getConnectionPool())) {
                throw new NutanixApiException("Connection for alias is not valid and no pool available", new NullPointerException("Connection is not valid for " + alias));
            }
            for (var poolMemberConnection: NutanixAbstractPoller.this.connectionManager.getConnectionPool(connection.get().getConnectionPool()) ) {
                if (poolMemberConnection.isPresent()) {
                    if (NutanixAbstractPoller.this.clientManager.validate(poolMemberConnection.get()).isEmpty()) {
                        LOG.info("Using Pooled Connection {} with Alias {}",
                                poolMemberConnection.get().getConnectionPool(),
                                poolMemberConnection.get().getAlias());
                        return NutanixAbstractPoller.this.clientManager.getClient(poolMemberConnection.get());
                    }
                }
            }
            throw new NutanixApiException("Connection for alias is not valid and no pool available", new NullPointerException("Connection is not valid for " + alias));
        }
    }
}
