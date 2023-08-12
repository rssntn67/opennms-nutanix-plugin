package org.opennms.nutanix.collection;


import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.collectors.CollectionRequest;
import org.opennms.integration.api.v1.collectors.ServiceCollectorFactory;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

import static org.opennms.nutanix.connections.ConnectionManager.ALIAS_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.PASSWORD_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.PRISM_URL_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.USERNAME_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.IGNORE_SSL_CERTIFICATE_VALIDATION_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.LENGTH_KEY;


abstract public class AbstractNutanixCollectorFactory<T extends AbstractNutanixServiceCollector> implements ServiceCollectorFactory<T> {

    protected final ClientManager clientManager;
    protected final ConnectionManager connectionManager;

    public AbstractNutanixCollectorFactory(final ClientManager clientManager, final ConnectionManager connectionManager) {
        this.clientManager = Objects.requireNonNull(clientManager);
        this.connectionManager = Objects.requireNonNull(connectionManager);
    }

    @Override
    public Map<String, Object> getRuntimeAttributes(CollectionRequest collectionRequest, Map<String, Object> parameters) {
        final var alias = Objects.requireNonNull(parameters.get(ALIAS_KEY), "Missing property: " + ALIAS_KEY);
        final var connection = this.connectionManager.getConnection(Objects.toString(alias))
                .orElseThrow(() -> new NullPointerException("Connection not found for alias: " + alias));

        final var runtimeAttributes = new HashMap<>(parameters);

        runtimeAttributes.put(PRISM_URL_KEY, connection.getPrismUrl());
        runtimeAttributes.put(USERNAME_KEY, connection.getUsername());
        runtimeAttributes.put(PASSWORD_KEY, connection.getPassword());
        runtimeAttributes.put(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY, String.valueOf(connection.isIgnoreSslCertificateValidation()));
        runtimeAttributes.put(LENGTH_KEY, String.valueOf(connection.getLength()));
        return runtimeAttributes;
    }

    @Override
    public Map<String, String> marshalParameters(Map<String, Object> parameters) {
        return parameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, entry -> entry.getValue().toString()));
    }

    @Override
    public Map<String, Object> unmarshalParameters(Map<String, String> parameters) {
        return parameters.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
