package org.opennms.nutanix.client.v08;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.collectors.CollectionRequest;
import org.opennms.integration.api.v1.collectors.CollectionSet;
import org.opennms.nutanix.client.api.ApiServiceCollector;

public class V08ApiServiceCollector implements ApiServiceCollector {
    @Override
    public void initialize() {

    }

    @Override
    public CompletableFuture<CollectionSet> collect(CollectionRequest collectionRequest, Map<String, Object> map) {
        return null;
    }
}
