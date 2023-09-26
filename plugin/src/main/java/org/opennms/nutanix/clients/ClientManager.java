
package org.opennms.nutanix.clients;

import java.util.Objects;
import java.util.Optional;

import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiClientProvider;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.connections.ConnectionValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientManager {
    private static final Logger LOG = LoggerFactory.getLogger(ClientManager.class);

    private final ApiClientProvider clientProvider;

    public ClientManager(ApiClientProvider providerA) {
        clientProvider = Objects.requireNonNull(providerA);
    }
    public Optional<ConnectionValidationError> validate(final ApiClientCredentials credentials) {
        boolean validated = clientProvider.validate(credentials);
        LOG.info("validate: {}, version {} - {}", credentials,clientProvider.getApiVersion().version,validated);
        if (validated) {
            return Optional.empty();
        }
        return Optional.of(new ConnectionValidationError("Credentials could not be validated"));
    }


    public ApiClientService getClient(ApiClientCredentials credentials) throws NutanixApiException{
        return clientProvider.client(credentials);
    }
}
