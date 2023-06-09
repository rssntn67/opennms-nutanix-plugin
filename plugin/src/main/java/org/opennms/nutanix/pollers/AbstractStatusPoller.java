
package org.opennms.nutanix.pollers;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerRequest;
import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.ServicePoller;
import org.opennms.integration.api.v1.pollers.ServicePollerFactory;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

public abstract class AbstractStatusPoller implements ServicePoller {
    private static final Logger LOG = LoggerFactory.getLogger(AbstractStatusPoller.class);

    public static final String ATTR_ALIAS = "alias";

    public static final String ATTR_PRISM_URL = "prismUrl";
    public static final String ATTR_API_KEY = "apiKey";

    private final ClientManager clientManager;

    protected AbstractStatusPoller(final ClientManager clientManager) {
        this.clientManager = Objects.requireNonNull(clientManager);
    }

    protected abstract CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException;

    @Override
    public final CompletableFuture<PollerResult> poll(final PollerRequest pollerRequest) {
        try {
            return this.poll(new Context(pollerRequest));

        } catch (final NutanixApiException e) {
            LOG.error("Nutanix orchestrator communication failed", e);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason(e.getMessage())
                                                                          .build());
        }
    }

    public static abstract class Factory<T extends AbstractStatusPoller> implements ServicePollerFactory<T> {

        private final ClientManager clientManager;

        private final ConnectionManager connectionManager;

        private final Class<T> clazz;

        protected Factory(final ClientManager clientManager,
                          final ConnectionManager connectionManager,
                          final Class<T> clazz) {
            this.clientManager = Objects.requireNonNull(clientManager);
            this.connectionManager = Objects.requireNonNull(connectionManager);

            this.clazz = Objects.requireNonNull(clazz);
        }

        protected abstract T createPoller(ClientManager clientManager);

        @Override
        public final T createPoller() {
            return this.createPoller(this.clientManager);
        }

        @Override
        public final String getPollerClassName() {
            return this.clazz.getCanonicalName();
        }



        //TODO get this to my factory
        @Override
        public final Map<String, String> getRuntimeAttributes(final PollerRequest pollerRequest) {
            final var alias = Objects.requireNonNull(pollerRequest.getPollerAttributes().get(ATTR_ALIAS), "Missing property: " + ATTR_ALIAS);
            final var connection = this.connectionManager.getConnection(alias)
                                                         .orElseThrow(() -> new NullPointerException("Connection not found for alias: " + alias));

            final var attrs = ImmutableMap.<String, String>builder();
            attrs.put(ATTR_PRISM_URL, connection.getPrismUrl());
            attrs.put(ATTR_API_KEY, connection.getApiKey());
            return attrs.build();
        }
    }

    public class Context {
        public final PollerRequest request;

        public Context(final PollerRequest request) {
            this.request = Objects.requireNonNull(request);
        }

        public NutanixApiClientCredentials getClientCredentials() {
            final var prismUrl = Objects.requireNonNull(this.request.getPollerAttributes().get(ATTR_PRISM_URL),
                                                               "Missing attribute: " + ATTR_PRISM_URL);
            final var apiKey = Objects.requireNonNull(this.request.getPollerAttributes().get(ATTR_API_KEY),
                                                      "Missing attribute: " + ATTR_API_KEY);

            return NutanixApiClientCredentials.builder()
                                                .withPrismUrl(prismUrl)
                                                .withApiKey(apiKey)
                                                .build();
        }

        public NutanixApiClient client() throws NutanixApiException {
            return AbstractStatusPoller.this.clientManager.getClient(this.getClientCredentials());
        }

        public Map<String, String> getPollerAttributes() {
            return this.request.getPollerAttributes();
        }
    }
}
