package org.opennms.nutanix.requisition;

import java.io.IOException;
import java.util.Map;
import java.util.Objects;

import org.opennms.integration.api.v1.config.requisition.Requisition;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisition;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionInterface;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionMetaData;
import org.opennms.integration.api.v1.config.requisition.immutables.ImmutableRequisitionNode;
import org.opennms.integration.api.v1.dao.NodeDao;
import org.opennms.integration.api.v1.requisition.RequisitionProvider;
import org.opennms.integration.api.v1.requisition.RequisitionRequest;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.internal.Utils;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.api.model.VMDisk;
import org.opennms.nutanix.client.api.model.VMNic;
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

    public final static String PARAMETER_ALIAS="alias";
    public final static String PARAMETER_LOCATION="location";

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
        final var alias = Objects.requireNonNull(parameters.get(PARAMETER_ALIAS), "Missing requisition parameter: alias");
        final var location = Objects.requireNonNullElse(parameters.get(PARAMETER_LOCATION), nodeDao.getDefaultLocationName());

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
                node.addCategory("NutanixCluster");
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
                node.addCategory("NutanixHost");
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
                        .setLocation(context.getLocation())
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("alias")
                                .setValue(context.getAlias())
                                .build())
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("name")
                                .setValue(vm.name)
                                .build()
                        ).addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("uuid")
                                .setValue(vm.uuid)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("clusterName")
                                .setValue(vm.clusterName)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("clusterUuid")
                                .setValue(vm.clusterUuid)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("hostName")
                                .setValue(vm.hostName)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("hostUuid")
                                .setValue(vm.hostUuid)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("state")
                                .setValue(vm.state)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("numThreadsPerCore")
                                .setValue(String.valueOf(vm.numThreadsPerCore))
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("memorySizeMib")
                                .setValue(String.valueOf(vm.memorySizeMib))
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("powerState")
                                .setValue(vm.powerState)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("numSockets")
                                .setValue(String.valueOf(vm.numSockets))
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("numVcpusPerSocket")
                                .setValue(String.valueOf(vm.numVcpusPerSocket))
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("machineType")
                                .setValue(vm.machineType)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("protectionType")
                                .setValue(vm.protectionType)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("hypervisorType")
                                .setValue(vm.hypervisorType)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("description")
                                .setValue(vm.description)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("kind")
                                .setValue(vm.kind)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("entityVersion")
                                .setValue(vm.entityVersion)
                                .build()
                        )
                        .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("specVersion")
                                .setValue(String.valueOf(vm.specVersion))
                                .build()
                        )
                        .addCategory("NutanixVM");

                node.addAsset("category", "NutanixVM");
                node.addAsset("cpu", "NutanixVM CPU: " + "numSockets:"+ vm.numSockets + " numVcpusPerSocket:" + vm.numVcpusPerSocket+" numThreadsPerCore:" + vm.numThreadsPerCore);
                node.addAsset("ram", "NutanixVM RAM: " +vm.memorySizeMib + "MB");

                for (VMDisk vdisk: vm.disks) {
                    node.addAsset("hdd"+(vdisk.deviceIndex+1), "Nutanix: " + vdisk.adapterType + ":" + vdisk.deviceType+vdisk.deviceIndex + " size:" + vdisk.diskSizeMib + "MB");
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("VMDisk."+vdisk.deviceIndex+".deviceIndex")
                            .setValue(String.valueOf(vdisk.deviceIndex))
                            .build())
                            .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                    .setContext(NUTANIX_METADATA_CONTEXT)
                                    .setKey("VMDisk."+vdisk.deviceIndex+".uuid")
                                    .setValue(vdisk.uuid)
                                    .build())
                            .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                    .setContext(NUTANIX_METADATA_CONTEXT)
                                    .setKey("VMDisk."+vdisk.deviceIndex+".adapterType")
                                    .setValue(vdisk.adapterType)
                                    .build())
                            .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                    .setContext(NUTANIX_METADATA_CONTEXT)
                                    .setKey("VMDisk."+vdisk.deviceIndex+".deviceType")
                                    .setValue(vdisk.deviceType)
                                    .build())
                            .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                    .setContext(NUTANIX_METADATA_CONTEXT)
                                    .setKey("VMDisk."+vdisk.deviceIndex+".diskSizeMib")
                                    .setValue(String.valueOf(vdisk.diskSizeMib))
                                    .build());
                }
                int nicIndex = 0;
                for (VMNic nic: vm.nics) {
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".nicIndex")
                            .setValue(String.valueOf(nicIndex))
                            .build());
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".kind")
                            .setValue(nic.kind)
                            .build());
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".nicType")
                            .setValue(nic.nicType)
                            .build());
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".macAddress")
                            .setValue(nic.macAddress)
                            .build());
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".name")
                            .setValue(nic.name)
                            .build());
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".vlanMode")
                            .setValue(nic.vlanMode)
                            .build());
                    node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("nic."+nicIndex+".isConnected")
                            .setValue(String.valueOf(nic.isConnected))
                            .build());
                    for (String ipAddress: nic.ipList) {
                        final var iface = ImmutableRequisitionInterface.newBuilder()
                                .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(ipAddress)));
                        iface.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                                .setContext(NUTANIX_METADATA_CONTEXT)
                                .setKey("nicIndex")
                                .setValue(String.valueOf(nicIndex)).build());
                        iface.addMonitoredService("NutanixEntity");
                        iface.addMonitoredService("NutanixVM");
                        node.addInterface(iface.build());
                    }

                }

                /*
                opennms=# \d assets
                                            Table "public.assets"
        Column         |           Type           | Collation | Nullable |              Default
-----------------------+--------------------------+-----------+----------+-----------------------------------
 category              | text                     |           | not null |
 manufacturer          | text                     |           |          |
 vendor                | text                     |           |          |
 modelnumber           | text                     |           |          |
 serialnumber          | text                     |           |          |
 description           | text                     |           |          |
 circuitid             | text                     |           |          |
 assetnumber           | text                     |           |          |
 operatingsystem       | text                     |           |          |
 rack                  | text                     |           |          |
 slot                  | text                     |           |          |
 port                  | text                     |           |          |
 region                | text                     |           |          |
 division              | text                     |           |          |
 department            | text                     |           |          |
 address1              | text                     |           |          |
 address2              | text                     |           |          |
 city                  | text                     |           |          |
 state                 | text                     |           |          |
 zip                   | text                     |           |          |
 building              | text                     |           |          |
 floor                 | text                     |           |          |
 room                  | text                     |           |          |
 vendorphone           | text                     |           |          |
 vendorfax             | text                     |           |          |
 vendorassetnumber     | text                     |           |          |
 userlastmodified      | character varying(20)    |           | not null |
 lastmodifieddate      | timestamp with time zone |           | not null |
 dateinstalled         | character varying(64)    |           |          |
 lease                 | text                     |           |          |
 leaseexpires          | character varying(64)    |           |          |
 supportphone          | text                     |           |          |
 maintcontract         | text                     |           |          |
 maintcontractexpires  | character varying(64)    |           |          |
 displaycategory       | text                     |           |          |
 notifycategory        | text                     |           |          |
 pollercategory        | text                     |           |          |
 thresholdcategory     | text                     |           |          |
 comment               | text                     |           |          |
 managedobjectinstance | text                     |           |          |
 managedobjecttype     | text                     |           |          |
 username              | text                     |           |          |
 password              | text                     |           |          |
 enable                | text                     |           |          |
 autoenable            | character(1)             |           |          |
 connection            | character varying(32)    |           |          |
 cpu                   | text                     |           |          |
 ram                   | text                     |           |          |
 storagectrl           | text                     |           |          |
 hdd1                  | text                     |           |          |
 hdd2                  | text                     |           |          |
 hdd3                  | text                     |           |          |
 hdd4                  | text                     |           |          |
 hdd5                  | text                     |           |          |
 hdd6                  | text                     |           |          |
 numpowersupplies      | character varying(1)     |           |          |
 inputpower            | character varying(11)    |           |          |
 additionalhardware    | text                     |           |          |
 admin                 | text                     |           |          |
 snmpcommunity         | character varying(32)    |           |          |
 rackunitheight        | character varying(2)     |           |          |
 country               | text                     |           |          |
 longitude             | double precision         |           |          |
 latitude              | double precision         |           |          |

                assets
                 */
                requisition.addNode(node.build());
            }

        }

        return requisition.build();
    }
    public static class Request implements RequisitionRequest {

        private boolean importVms = true;
        private boolean importHosts = false;

        private boolean importClusters = false;

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
