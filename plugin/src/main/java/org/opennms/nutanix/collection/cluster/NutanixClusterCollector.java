package org.opennms.nutanix.collection.cluster;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.collectors.CollectionRequest;
import org.opennms.integration.api.v1.collectors.CollectionSet;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.collection.AbstractNutanixServiceCollector;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixClusterCollector extends AbstractNutanixServiceCollector {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixClusterCollector.class);

    public NutanixClusterCollector(ClientManager clientManager, ConnectionManager connectionManager) {
        super(clientManager, connectionManager);
    }

    @Override
    public void initialize() {
    }

    @Override
    public CompletableFuture<CollectionSet> collect(CollectionRequest request, Map<String, Object> attributes) {
        return null;
    }
}
