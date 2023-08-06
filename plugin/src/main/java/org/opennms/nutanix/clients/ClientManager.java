
package org.opennms.nutanix.clients;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.connections.ConnectionValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientManager {
    private static final Logger LOG = LoggerFactory.getLogger(ClientManager.class);

    Map<ApiVersion.Version, NutanixApiClientProvider> clientProviderMap = new HashMap<>();

    public ClientManager(NutanixApiClientProvider... apiClientProviders) {
        Objects.requireNonNull(apiClientProviders);
        for (NutanixApiClientProvider provider: apiClientProviders) {
            clientProviderMap.put(provider.getApiVersion().version, provider);
        }
    }

    public NutanixApiClientProvider getProvider(ApiVersion.Version version) throws NutanixApiException {
        if (clientProviderMap.containsKey(version))
            return clientProviderMap.get(version);
        throw new NutanixApiException("Version not Supported: " + version);
    }
    public NutanixApiClient client(final NutanixApiClientCredentials credentials, ApiVersion.Version version) throws NutanixApiException {
        return getProvider(version).client(credentials);
    }
    public Optional<ConnectionValidationError> validate(final NutanixApiClientCredentials credentials) {

        for (NutanixApiClientProvider provider: clientProviderMap.values()) {
            try {
                provider.validate(credentials);
                return Optional.empty();
            } catch (NutanixApiException e) {
                LOG.info("validate: cannot validate: {}", credentials.prismUrl);
            }
        }

        return Optional.of(new ConnectionValidationError("Credentials could not be validated"));
    }


    public NutanixApiClient getClient(NutanixApiClientCredentials credentials) throws NutanixApiException{
        if (clientProviderMap.containsKey(ApiVersion.Version.VERSION_3))
            return client(credentials, ApiVersion.Version.VERSION_3);
        if (clientProviderMap.containsKey(ApiVersion.Version.VERSION_2))
            return client(credentials, ApiVersion.Version.VERSION_2);
        if (clientProviderMap.containsKey(ApiVersion.Version.VERSION_1))
            client(credentials, ApiVersion.Version.VERSION_1);
        if (clientProviderMap.containsKey(ApiVersion.Version.VERSION_0_8))
            client(credentials, ApiVersion.Version.VERSION_0_8);
        throw new NutanixApiException("Cannot instantiate NutanixApiClient for" + credentials.prismUrl);
    }
}
