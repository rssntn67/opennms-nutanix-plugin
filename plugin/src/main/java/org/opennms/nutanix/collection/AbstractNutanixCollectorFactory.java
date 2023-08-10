package org.opennms.nutanix.collection;

import static org.opennms.nutanix.pollers.AbstractStatusPoller.ATTR_ALIAS;
import static org.opennms.nutanix.pollers.AbstractStatusPoller.ATTR_IGNORE_SSL_VALIDATION;
import static org.opennms.nutanix.pollers.AbstractStatusPoller.ATTR_LENGTH;
import static org.opennms.nutanix.pollers.AbstractStatusPoller.ATTR_PASSWORD;
import static org.opennms.nutanix.pollers.AbstractStatusPoller.ATTR_PRISM_URL;
import static org.opennms.nutanix.pollers.AbstractStatusPoller.ATTR_USERNAME;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.collectors.CollectionRequest;
import org.opennms.integration.api.v1.collectors.ServiceCollectorFactory;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

abstract public class AbstractNutanixCollectorFactory<T extends AbstractNutanixServiceCollector> implements ServiceCollectorFactory<T> {

    protected final ClientManager clientManager;
    protected final ConnectionManager connectionManager;

    public AbstractNutanixCollectorFactory(final ClientManager clientManager, final ConnectionManager connectionManager) {
        this.clientManager = Objects.requireNonNull(clientManager);
        this.connectionManager = Objects.requireNonNull(connectionManager);
    }

    @Override
    public Map<String, Object> getRuntimeAttributes(CollectionRequest collectionRequest, Map<String, Object> parameters) {
        final var alias = Objects.requireNonNull(parameters.get(ATTR_ALIAS), "Missing property: " + ATTR_ALIAS);
        final var connection = this.connectionManager.getConnection(Objects.toString(alias))
                .orElseThrow(() -> new NullPointerException("Connection not found for alias: " + alias));

        final var runtimeAttributes = new HashMap<>(parameters);

        runtimeAttributes.put(ATTR_PRISM_URL, connection.getPrismUrl());
        runtimeAttributes.put(ATTR_USERNAME, connection.getUsername());
        runtimeAttributes.put(ATTR_PASSWORD, connection.getPassword());
        runtimeAttributes.put(ATTR_IGNORE_SSL_VALIDATION, String.valueOf(connection.isIgnoreSslCertificateValidation()));
        runtimeAttributes.put(ATTR_LENGTH, String.valueOf(connection.getLength()));
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
