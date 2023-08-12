package org.opennms.nutanix.requisition;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.opennms.integration.api.v1.config.requisition.Requisition;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisition;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionMetaData;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionNode;
import org.opennms.integration.api.v1.dao.NodeDao;
import org.opennms.integration.api.v1.requisition.RequisitionProvider;
import org.opennms.integration.api.v1.requisition.RequisitionRequest;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.Connection;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.base.Strings;

public class NutanixRequisitionProvider implements RequisitionProvider {

    public final static String TYPE = "nutanix";

    private static final Logger LOG = LoggerFactory.getLogger(NutanixRequisitionProvider.class);

    public static final String NUTANIX_METADATA_CONTEXT = "nutanix";
    public static final String PARAMETER_FOREIGN_SOURCE = "foreignSource";

    public final static String PARAMETER_IMPORT_VMS="importVms";
    public final static String PARAMETER_IMPORT_ALL_VMS="importALLVms";
    public final static String PARAMETER_IMPORT_HOSTS ="importHosts";

    public final static String PARAMETER_IMPORT_CLUSTERS="importClusters";

    private final NodeDao nodeDao;

    private final ClientManager clientManager;

    private final ConnectionManager connectionManager;

    public NutanixRequisitionProvider(NodeDao nodeDao, ClientManager clientManager, ConnectionManager connectionManager) {
        this.nodeDao = Objects.requireNonNull(nodeDao);
        this.clientManager = Objects.requireNonNull(clientManager);
        this.connectionManager = Objects.requireNonNull(connectionManager);
    }

    @Override
    public String getType() {
        return TYPE;
    }

    @Override
    public final RequisitionRequest getRequest(final Map<String, String> parameters) {
        final var alias = Objects.requireNonNull(parameters.get("alias"), "Missing requisition parameter: alias");
        final var location = Objects.requireNonNullElse(parameters.get("location"), nodeDao.getDefaultLocationName());

        final var connection = this.connectionManager.getConnection(alias)
                .orElseThrow(() -> new NullPointerException("Connection not found for alias: " + alias));

        final var request = new Request(connection);
        request.setLocation(location);

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
    public final Requisition getRequisition(final RequisitionRequest rawRequest) {
        final var request = (Request) rawRequest;

        try {
            return this.handleRequest(new RequestContext(request));

        } catch (NutanixApiException e) {
            LOG.error("Nutanix Prism communication failed", e);
            throw new RuntimeException("Nutanix prism communication failed", e);
        }
    }
    protected Requisition handleRequest(final RequestContext context) throws NutanixApiException {
        var request = (Request) context.getRequest();

        final var requisition = ImmutableRequisition.newBuilder()
                .setForeignSource(context.getForeignSource());

        ApiClientService apiClientService = context.getClient();
        if (request.importClusters) {
            for (Cluster cluster: apiClientService.getClusters()) {
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
            for (Host host: apiClientService.getHosts()) {
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
            for (VM vm: apiClientService.getVMS()) {
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
    public static class Request implements RequisitionRequest {

        private boolean importVms = true;
        private boolean importHosts = true;

        private boolean importClusters = true;

        private boolean importAllVms = false;

        private String foreignSource;

        private String alias;

        private String prismUrl;

        private String username;

        private String password;

        private boolean ignoreSslCertificateValidation;
        private String location;

        private int length;

        public Request() {
        }

        public Request(final Connection connection) {
            this.alias = Objects.requireNonNull(connection.getAlias());
            this.prismUrl = Objects.requireNonNull(connection.getPrismUrl());
            this.username = Objects.requireNonNull(connection.getUsername());
            this.password = Objects.requireNonNull(connection.getPassword());
            this.ignoreSslCertificateValidation = connection.isIgnoreSslCertificateValidation();
            this.length = connection.getLength();
        }


        public String getForeignSource() {
            return Strings.isNullOrEmpty(this.foreignSource) ? this.getDefaultForeignSource() : this.foreignSource;
        }

        public void setForeignSource(final String foreignSource) {
            this.foreignSource = foreignSource;
        }

        public String getAlias() {
            return this.alias;
        }

        public String getPrismUrl() {
            return this.prismUrl;
        }

        public String getUsername() {
            return this.username;
        }

        public String getPassword() {
            return this.password;
        }

        public String getLocation() {
            return this.location;
        }

        public void setLocation(String location) {
            this.location = Objects.requireNonNull(location);
        }

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

        protected String getDefaultForeignSource() {
            return String.format("%s-%s", TYPE, this.getAlias());
        }
    }

    public class RequestContext {
        private final Request request;

        public RequestContext(final Request request) {
            this.request = Objects.requireNonNull(request);
        }

        public ApiClientService getClient() throws NutanixApiException {
            return clientManager.getClient(ApiClientCredentials.builder()
                    .withPrismUrl(this.request.getPrismUrl())
                    .withUsername(this.request.getUsername())
                    .withPassword(this.request.getPassword())
                    .withIgnoreSslCertificateValidation(this.request.ignoreSslCertificateValidation)
                    .withLength(this.request.length)
                    .build());
        }

        public Request getRequest() {
            return this.request;
        }

        public String getForeignSource() {
            return this.request.getForeignSource();
        }

        public String getAlias() {
            return this.request.getAlias();
        }

        public String getLocation() {
            return this.request.getLocation();
        }
    }

    @Override
    public final byte[] marshalRequest(final RequisitionRequest request) {
        final var mapper = new ObjectMapper();
        try {
            return mapper.writeValueAsBytes(request);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public final RequisitionRequest unmarshalRequest(final byte[] bytes) {
        final var mapper = new ObjectMapper();
        try {
            return mapper.readValue(bytes, RequisitionRequest.class);
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
    }

}
