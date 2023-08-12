package org.opennms.nutanix.collection;

import static org.opennms.nutanix.connections.ConnectionManager.IGNORE_SSL_CERTIFICATE_VALIDATION_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.LENGTH_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.PASSWORD_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.PRISM_URL_KEY;
import static org.opennms.nutanix.connections.ConnectionManager.USERNAME_KEY;

import java.util.Map;
import java.util.concurrent.CompletableFuture;

import org.opennms.integration.api.v1.collectors.CollectionSet;
import org.opennms.integration.api.v1.collectors.immutables.ImmutableNumericAttribute;
import org.opennms.integration.api.v1.collectors.immutables.ImmutableStringAttribute;
import org.opennms.integration.api.v1.collectors.resource.NodeResource;
import org.opennms.integration.api.v1.collectors.resource.NumericAttribute;
import org.opennms.integration.api.v1.collectors.resource.Resource;
import org.opennms.integration.api.v1.collectors.resource.StringAttribute;
import org.opennms.integration.api.v1.collectors.resource.immutables.ImmutableCollectionSet;
import org.opennms.integration.api.v1.collectors.resource.immutables.ImmutableCollectionSetResource;
import org.opennms.integration.api.v1.collectors.resource.immutables.ImmutableNodeResource;
import org.opennms.nutanix.client.api.ApiClient;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiServiceCollector;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Aggregate;
import org.opennms.nutanix.client.api.model.Traffic;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

public abstract class AbstractNutanixServiceCollector implements ApiServiceCollector {

    public static final String SERVICE_INTERVAL = "SERVICE_INTERVAL";

    protected final ClientManager clientManager;
    protected final ConnectionManager connectionManager;

    public AbstractNutanixServiceCollector(ClientManager clientManager, ConnectionManager connectionManager) {
        this.clientManager = clientManager;
        this.connectionManager = connectionManager;
    }

    public static void addNumAttr(ImmutableCollectionSetResource.Builder<? extends Resource> builder, String groupId,
                                  String name, Number value) {
        if(value != null) {
            builder.addNumericAttribute(createNumAttr(groupId, name, value.doubleValue()));
        }

    }

    public static void addNumAttr(ImmutableCollectionSetResource.Builder<? extends Resource> builder, String groupId,
                            String name, Number value, long milliseconds) {
        if(value != null) {
            builder.addNumericAttribute(createNumAttr(groupId, name, value.doubleValue() * 1000 / milliseconds));
        }
    }

    public static StringAttribute createStringAttribute(String groupId, String name, String value) {
        return ImmutableStringAttribute.newBuilder().setGroup(groupId).setName(name).setValue(value).build();
    }

    public static NumericAttribute createNumAttr(String groupId, String name, double value) {
        return ImmutableNumericAttribute.newBuilder().setGroup(groupId).setName(name).setValue(value)
                .setType(NumericAttribute.Type.GAUGE).build();
    }

    public static void addAggregate(ImmutableCollectionSetResource.Builder<? extends Resource> builder, String groupId,
                              String prefix, Aggregate aggregate) {
        if(aggregate != null) {
            addNumAttr(builder, groupId, prefix + "Min", aggregate.getMin());
            addNumAttr(builder, groupId, prefix + "Max", aggregate.getMax());
            addNumAttr(builder, groupId, prefix + "Avg", aggregate.getAverage());
        }
    }

    public static void addTraffic(ImmutableCollectionSetResource.Builder<? extends Resource> builder, String groupId,
                            String prefix, Traffic traffic, long milliseconds) {
        if(traffic != null) {
            addNumAttr(builder, groupId, prefix + "BytesRx", traffic.getBytesRx(), milliseconds);
            addNumAttr(builder, groupId, prefix + "BytesTx", traffic.getBytesTx(), milliseconds);
            addNumAttr(builder, groupId, prefix + "PacketsRx", traffic.getPacketsRx(), milliseconds);
            addNumAttr(builder, groupId, prefix + "PacketsTx", traffic.getPacketsTx(), milliseconds);
        }
    }

    protected ApiClient getClient(Map<String, Object> attributes) throws NutanixApiException {
        return clientManager.getClient(getCredentials(attributes));
    }

    private static ApiClientCredentials getCredentials(Map<String, Object> attributes) {
        return ApiClientCredentials.builder()
                .withPrismUrl(attributes.get(PRISM_URL_KEY).toString())
                .withUsername(attributes.get(USERNAME_KEY).toString())
                .withPassword(attributes.get(PASSWORD_KEY).toString())
                .withIgnoreSslCertificateValidation(Boolean.parseBoolean(attributes.get(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY).toString()))
                .withLength(Integer.parseInt(attributes.get(LENGTH_KEY).toString()))
                .build();
    }

    public static CompletableFuture<CollectionSet> createFailedCollectionSet(ImmutableNodeResource nodeResource, long timestamp) {
        return CompletableFuture.completedFuture(ImmutableCollectionSet.newBuilder()
                .addCollectionSetResource(ImmutableCollectionSetResource.newBuilder(NodeResource.class)
                        .setResource(nodeResource).build())
                .setTimestamp(timestamp).setStatus(CollectionSet.Status.FAILED).build());
    }

}
