
package org.opennms.nutanix.clients;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiClientProvider;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.connections.ConnectionValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientManager {
    private static final Logger LOG = LoggerFactory.getLogger(ClientManager.class);

    private final Map<ApiVersion.Version, ApiClientProvider> clientProviderMap = new HashMap<>();

    public ClientManager(ApiClientProvider providerA, ApiClientProvider providerB, ApiClientProvider providerC, ApiClientProvider providerD) {
        Objects.requireNonNull(providerA);
        Objects.requireNonNull(providerB);
        Objects.requireNonNull(providerC);
        Objects.requireNonNull(providerD);
        LOG.warn("constructor: {}", providerA.getApiVersion().version);
        LOG.warn("constructor: {}", providerB.getApiVersion().version);
        LOG.warn("constructor: {}", providerC.getApiVersion().version);
        LOG.warn("constructor: {}", providerD.getApiVersion().version);
        clientProviderMap.put(providerD.getApiVersion().version, providerD);
        clientProviderMap.put(providerC.getApiVersion().version, providerC);
        clientProviderMap.put(providerB.getApiVersion().version, providerB);
        clientProviderMap.put(providerA.getApiVersion().version, providerA);
    }
    public ApiClientProvider getProvider(ApiVersion.Version version) throws NutanixApiException {
        LOG.warn("getProvider: supported API: {}", clientProviderMap.keySet());
        if (clientProviderMap.containsKey(version))
            return clientProviderMap.get(version);
        throw new NutanixApiException("Version not Supported: " + version);
    }
    public ApiClientService client(final ApiClientCredentials credentials, ApiVersion.Version version) throws NutanixApiException {
        return getProvider(version).client(credentials);
    }

    private boolean validate (final ApiClientCredentials credentials, ApiVersion.Version version) {
        try {
            boolean validated = getProvider(version).validate(credentials);
            LOG.info("validate: {}, version {} - {}", credentials,version,validated);
            return validated;
        } catch (NutanixApiException e) {
            LOG.warn("validate: cannot validate {}, {}: exception: {}",credentials,version, e.getMessage() );
        }
        return false;
    }
    public Optional<ConnectionValidationError> validate(final ApiClientCredentials credentials) {
        if (validate(credentials, ApiVersion.Version.VERSION_3)) {
            return Optional.empty();
        }
        if (validate(credentials, ApiVersion.Version.VERSION_2)) {
            return Optional.empty();
        }
        if (validate(credentials, ApiVersion.Version.VERSION_1)) {
            return Optional.empty();
        }
        if (validate(credentials, ApiVersion.Version.VERSION_0_8)) {
            return Optional.empty();
        }
        return Optional.of(new ConnectionValidationError("Credentials could not be validated"));
    }


    public ApiClientService getClient(ApiClientCredentials credentials) throws NutanixApiException{
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
