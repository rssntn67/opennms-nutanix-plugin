package org.opennms.nutanix.collection.cluster;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.collection.AbstractNutanixCollectorFactory;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixClusterCollectorFactory extends AbstractNutanixCollectorFactory<NutanixClusterCollector> {

    public NutanixClusterCollectorFactory(ClientManager clientManager, ConnectionManager connectionManager) {
        super(clientManager, connectionManager);
    }

    @Override
    public NutanixClusterCollector createCollector() {
        return new NutanixClusterCollector(clientManager, connectionManager);
    }

    @Override
    public String getCollectorClassName() {
        return NutanixClusterCollector.class.getCanonicalName();
    }

}
