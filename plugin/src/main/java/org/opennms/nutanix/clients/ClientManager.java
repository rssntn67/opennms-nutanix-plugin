
package org.opennms.nutanix.clients;

import java.util.Objects;
import java.util.Optional;

import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiClientProvider;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.connections.Connection;
import org.opennms.nutanix.connections.ConnectionValidationError;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ClientManager {
    private static final Logger LOG = LoggerFactory.getLogger(ClientManager.class);

    private final ApiClientProvider clientProvider;

    public ClientManager(ApiClientProvider provider) {
        clientProvider = Objects.requireNonNull(provider);
    }
    public Optional<ConnectionValidationError> validate(Connection connection) {
        boolean validated = clientProvider.validate(asNutanixCredentials(connection));
        LOG.info("validate: {}, version {} - {}", connection.getAlias(), clientProvider.getApiVersion().version,validated);
        if (validated) {
            return Optional.empty();
        }
        return Optional.of(new ConnectionValidationError("Connection could not be validated"));
    }


    public ApiClientService getClient(Connection connection) throws NutanixApiException{
        return clientProvider.client(asNutanixCredentials(connection));
    }

    private static ApiClientCredentials asNutanixCredentials(Connection connection) {
        return ApiClientCredentials.builder()
                .withUsername(connection.getUsername())
                .withPassword(connection.getPassword())
                .withPrismUrl(connection.getPrismUrl())
                .withIgnoreSslCertificateValidation(connection.isIgnoreSslCertificateValidation())
                .withLength(connection.getLength())
                .build();
    }

}
