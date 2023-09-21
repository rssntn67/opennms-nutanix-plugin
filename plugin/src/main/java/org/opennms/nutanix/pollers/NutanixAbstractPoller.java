
package org.opennms.nutanix.pollers;

import static org.opennms.nutanix.connections.ConnectionManager.ALIAS_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.IGNORE_SSL_CERTIFICATE_VALIDATION_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.LENGTH_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.PASSWORD_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.PRISM_URL_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.USERNAME_KEY;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.pollers.PollerRequest;
import org.opennms.integration.api.v1.pollers.PollerResult;
import org.opennms.integration.api.v1.pollers.ServicePoller;
import org.opennms.integration.api.v1.pollers.ServicePollerFactory;
import org.opennms.integration.api.v1.pollers.Status;
import org.opennms.integration.api.v1.pollers.immutables.ImmutablePollerResult;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Entity;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.ImmutableMap;

public abstract class NutanixAbstractPoller implements ServicePoller {
    private static final Logger LOG = LoggerFactory.getLogger(NutanixAbstractPoller.class);
    private final ClientManager clientManager;

    private final static String UUID_KEY = "uuid";
    private final static String TYPE_KEY = "type";

    protected NutanixAbstractPoller(final ClientManager client) {
        this.clientManager = Objects.requireNonNull(client);
    }

    protected abstract CompletableFuture<PollerResult> poll(final Context context) throws NutanixApiException;

    @Override
    public final CompletableFuture<PollerResult> poll(final PollerRequest pollerRequest) {
        try {
            LOG.info("poll: {} {}", pollerRequest.getAddress().getHostAddress(), pollerRequest.getServiceName());
            return this.poll(new Context(pollerRequest));
        } catch (final NutanixApiException e) {
            LOG.error("Nutanix prism communication failed", e);
            return CompletableFuture.completedFuture(ImmutablePollerResult.newBuilder()
                                                                          .setStatus(Status.Down)
                                                                          .setReason(e.getMessage())
                                                                          .build());
        }
    }

    public static abstract class Factory<T extends NutanixAbstractPoller> implements ServicePollerFactory<T> {

        private final ClientManager clientManager;

        private final ConnectionManager connectionManager;

        private final Class<T> clazz;

        protected Factory(final ClientManager client,
                          final ConnectionManager connectionManager,
                          final Class<T> clazz) {
            this.clientManager = Objects.requireNonNull(client);
            this.connectionManager = Objects.requireNonNull(connectionManager);

            this.clazz = Objects.requireNonNull(clazz);
        }

        protected abstract T createPoller(ClientManager clientManager);

        @Override
        public final T createPoller() {
            LOG.info("Factory::createPoller -> class {}", getPollerClassName());
            return this.createPoller(this.clientManager);
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
            final var connection = this.connectionManager.getConnection(alias)
                                                         .orElseThrow(() -> new NullPointerException("Connection not found for alias: " + alias));
            LOG.info("Factory::getRuntimeAttributes -> connection: {}, class {}", connection, getPollerClassName());

            final var attrs = ImmutableMap.<String,String>builder();
            attrs.put(PRISM_URL_KEY, connection.getPrismUrl());
            attrs.put(USERNAME_KEY, connection.getUsername());
            attrs.put(PASSWORD_KEY, connection.getPassword());
            attrs.put(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY, String.valueOf(connection.isIgnoreSslCertificateValidation()));
            attrs.put(LENGTH_KEY, String.valueOf(connection.getLength()));
            attrs.put(UUID_KEY, uuid);
            attrs.put(TYPE_KEY, type);

            return attrs.build();
        }
    }

    public class Context {
        public final PollerRequest request;

        public Context(final PollerRequest request) {
            this.request = Objects.requireNonNull(request);
        }

        public ApiClientCredentials getClientCredentials() {
            final var prismUrl = Objects.requireNonNull(this.request.getPollerAttributes().get(PRISM_URL_KEY),
                                                               "Missing attribute: " + PRISM_URL_KEY);
            final var username = Objects.requireNonNull(this.request.getPollerAttributes().get(USERNAME_KEY),
                                                      "Missing attribute: " + USERNAME_KEY);

            final var password = Objects.requireNonNull(this.request.getPollerAttributes().get(PASSWORD_KEY),
                    "Missing attribute: " + PASSWORD_KEY);

            final var ignoreSslCertificateValidation = Objects.requireNonNull(this.request.getPollerAttributes().get(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY),
                    "Missing attribute: " + IGNORE_SSL_CERTIFICATE_VALIDATION_KEY);

            final var length = Objects.requireNonNull(this.request.getPollerAttributes().get(LENGTH_KEY),
                    "Missing attribute: " + LENGTH_KEY);


            ApiClientCredentials credentials = ApiClientCredentials.builder()
                    .withPrismUrl(prismUrl)
                    .withUsername(username)
                    .withPassword(password)
                    .withIgnoreSslCertificateValidation(Boolean.parseBoolean(ignoreSslCertificateValidation))
                    .withLength(Integer.parseInt(length))
                    .build();

            LOG.info("Context::getClientCredentials -> {}", credentials);

            return credentials;
        }

        public String getNutanixUuid() {
            final var uuid= Objects.requireNonNull(this.request.getPollerAttributes().get(UUID_KEY),
                    "Missing attribute: " + UUID_KEY);
            LOG.info("Context::getNutanixUuid: {}", uuid);
            return uuid;
        }
        public Entity.EntityType getNutanixEntityType() {
            final var type = Objects.requireNonNull(this.request.getPollerAttributes().get(TYPE_KEY),
                    "Missing attribute: " + TYPE_KEY);
            LOG.info("Context::getNutanixEntityType: {}", type);
            return Entity.EntityType.valueOf(type);
        }

        public ApiClientService client() throws NutanixApiException {
            LOG.info("Context::client");
            return NutanixAbstractPoller.this.clientManager.getClient(this.getClientCredentials());
        }
    }
}
