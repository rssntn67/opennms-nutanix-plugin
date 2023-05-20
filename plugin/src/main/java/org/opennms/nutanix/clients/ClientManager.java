
package org.opennms.nutanix.clients;

import java.time.Duration;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.ExecutionException;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.connections.ConnectionValidationError;
import org.osgi.framework.BundleContext;
import org.osgi.framework.FrameworkUtil;
import org.osgi.framework.InvalidSyntaxException;
import org.osgi.framework.ServiceEvent;
import org.osgi.framework.ServiceListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;

public class ClientManager implements ServiceListener {
    private static final Logger LOG = LoggerFactory.getLogger(ClientManager.class);
    private static final String NUTANIX_API_CLIENT_PROVIDER_SERVICE_FILTER = "(objectClass=org.opennms.nutanix.client.api.NutanixApiClientProvider)";

    private final NutanixApiClientProvider clientProvider;

    private final Cache<NutanixApiClientCredentials, ClientEntry> clients;

    public ClientManager(final NutanixApiClientProvider clientProvider, final long cacheRetentionMs) {
        this.clientProvider = Objects.requireNonNull(clientProvider);
        this.clients = CacheBuilder.newBuilder()
                .expireAfterAccess(Duration.ofMillis(cacheRetentionMs))
                .build();

        final BundleContext bundleContext = FrameworkUtil.getBundle(ClientManager.class).getBundleContext();
        try {
            bundleContext.addServiceListener(this, NUTANIX_API_CLIENT_PROVIDER_SERVICE_FILTER);
            LOG.debug("ClientManager: added service listener");
        } catch (InvalidSyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void serviceChanged(ServiceEvent event) {
        this.clients.invalidateAll();
        LOG.debug("ClientManager: Dependent service was changed, clearing cache");
    }

    public void destroy() {
        final BundleContext bundleContext = FrameworkUtil.getBundle(ClientManager.class).getBundleContext();
        bundleContext.removeServiceListener(this);
        LOG.debug("ClientManager: removed service listener");
    }

    public NutanixApiClient getClient(final NutanixApiClientCredentials credentials) throws NutanixApiException {
        try {
            return this.clients.get(credentials, () -> new ClientEntry(this.clientProvider.client(credentials)))
                    .asClient()
                    .orElseThrow(() -> new NutanixApiException("Not a partner client"));
        } catch (ExecutionException e) {
            throw new NutanixApiException("Error creating partner client", e);
        }
    }

    public Optional<ConnectionValidationError> validate(final NutanixApiClientCredentials credentials) {
        try {
            this.clientProvider.client(credentials);
            return Optional.empty();
        } catch (NutanixApiException e) {}

        return Optional.of(new ConnectionValidationError("Credentials could not be validated"));
    }

    public void purgeClient(NutanixApiClientCredentials credentials) {
        this.clients.invalidate(credentials);
    }


    private static class ClientEntry {

        private final NutanixApiClient client;

        private ClientEntry(final NutanixApiClient client) {
            this.client = Objects.requireNonNull(client);
        }

        public Optional<NutanixApiClient> asClient() {
            return Optional.of(this.client);
        }
    }

}
