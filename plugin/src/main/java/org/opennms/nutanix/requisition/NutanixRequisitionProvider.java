package org.opennms.nutanix.requisition;

import java.util.Map;

import org.opennms.integration.api.v1.config.requisition.Requisition;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisition;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionMetaData;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionNode;
import org.opennms.integration.api.v1.dao.NodeDao;
import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.Connection;
import org.opennms.nutanix.connections.ConnectionManager;

public class NutanixRequisitionProvider extends AbstractRequisitionProvider<AbstractRequisitionProvider.Request> {

    public final static String TYPE = "nutanix";

    public final static String PARAMETER_IMPORT_VMS="importVms";
    public final static String PARAMETER_IMPORT_ALL_VMS="importALLVms";
    public final static String PARAMETER_IMPORT_HOSTS ="importHosts";

    public final static String PARAMETER_IMPORT_CLUSTERS="importClusters";

    public NutanixRequisitionProvider(NodeDao nodeDao, ClientManager clientManager, ConnectionManager connectionManager, Class<? extends AbstractRequisitionProvider.Request> requestClass) {
        super(nodeDao, clientManager, connectionManager, requestClass);
    }

    @Override
    public String getType() {
        return TYPE;
    }


    @Override
    protected NutanixRequisitionProvider.Request createRequest(Connection connection, Map<String, String> parameters) {
        final var request = new Request(connection);

        if (parameters.containsKey(PARAMETER_FOREIGN_SOURCE)) {
            request.setForeignSource(parameters.get(PARAMETER_FOREIGN_SOURCE));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_VMS)) {
            request.setImportVms(Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_VMS)));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_ALL_VMS)) {
            request.setImportAllVms(Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_ALL_VMS)));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_HOSTS)) {
            request.setImportHosts(Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_HOSTS)));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_CLUSTERS)) {
            request.setImportClusters(Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_CLUSTERS)));
        }

        return request;
    }

    @Override
    protected Requisition handleRequest(final RequestContext context) throws NutanixApiException {
        var request = (Request) context.getRequest();

        final var requisition = ImmutableRequisition.newBuilder()
                .setForeignSource(context.getForeignSource());

        NutanixApiClient apiClient = context.getClient();
        if (request.importClusters) {
            for (Cluster cluster: apiClient.getClusters()) {
                final var node = ImmutableRequisitionNode.newBuilder()
                        .setNodeLabel(cluster.name)
                        .setForeignId(cluster.uuid)
                        .setLocation(context.getLocation());
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("alias")
                        .setValue(context.getAlias())
                        .build());
                requisition.addNode(node.build());
            }
        }
        if (request.importHosts) {
            for (Host host: apiClient.getHosts()) {
                final var node = ImmutableRequisitionNode.newBuilder()
                        .setNodeLabel(host.name)
                        .setForeignId(host.uuid)
                        .setLocation(context.getLocation());
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("alias")
                        .setValue(context.getAlias())
                        .build());
                requisition.addNode(node.build());
            }
        }
        if (request.importVms) {
            for (VM vm: apiClient.getVMS()) {
                if (!request.importAllVms && !vm.powerState.equalsIgnoreCase("ON"))
                    continue;
                final var node = ImmutableRequisitionNode.newBuilder()
                        .setNodeLabel(vm.name)
                        .setForeignId(vm.uuid)
                        .setLocation(context.getLocation());
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("alias")
                        .setValue(context.getAlias())
                        .build());
                requisition.addNode(node.build());
            }

        }

        return requisition.build();
    }
    public static class Request extends AbstractRequisitionProvider.Request {

        private boolean importVms = true;
        private boolean importHosts = true;

        private boolean importClusters = true;

        private boolean importAllVms = false;

        public void setImportVms(boolean importVms) {
            this.importVms = importVms;
        }

        public void setImportHosts(boolean importHosts) {
            this.importHosts = importHosts;
        }

        public void setImportClusters(boolean importClusters) {
            this.importClusters = importClusters;
        }

        public void setImportAllVms(boolean importAllVms) {
            this.importAllVms = importAllVms;
        }

        public Request() {
        }

        public Request(final Connection connection) {
            super(connection);
        }

        @Override
        protected String getDefaultForeignSource() {
            return String.format("%s-%s", TYPE, this.getAlias());
        }
    }

}
