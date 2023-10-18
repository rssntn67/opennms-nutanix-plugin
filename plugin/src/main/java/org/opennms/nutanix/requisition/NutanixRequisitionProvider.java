package org.opennms.nutanix.requisition;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.regex.Pattern;

import org.opennms.integration.api.v1.config.requisition.Requisition;
import org.opennms.integration.api.v1.config.requisition.RequisitionNode;
import org.opennms.integration.api.v1.config.requisition.SnmpPrimaryType;
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
import org.opennms.nutanix.client.api.model.ClusterHttpProxy;
import org.opennms.nutanix.client.api.model.ClusterHttpWhiteProxy;
import org.opennms.nutanix.client.api.model.ClusterHypervisor;
import org.opennms.nutanix.client.api.model.ClusterSoftware;
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

import lombok.Getter;

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

    public final static String PARAMETER_MATCH_VM="matchVM";

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
        request.location=location;

        if (parameters.containsKey(PARAMETER_FOREIGN_SOURCE)) {
            request.foreignSource=parameters.get(PARAMETER_FOREIGN_SOURCE);
        }

        if (parameters.containsKey(PARAMETER_IMPORT_VMS)) {
            request.importVms=Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_VMS));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_ALL_VMS)) {
            request.importAllVms=Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_ALL_VMS));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_HOSTS)) {
            request.importHosts=Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_HOSTS));
        }

        if (parameters.containsKey(PARAMETER_IMPORT_CLUSTERS)) {
            request.importClusters=Boolean.parseBoolean(parameters.get(PARAMETER_IMPORT_CLUSTERS));
        }

        if (parameters.containsKey(PARAMETER_MATCH_VM)) {
            request.matchVM=parameters.get(PARAMETER_MATCH_VM);
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

    private RequisitionNode getClusterNode(Cluster cluster, RequestContext context) {
        final var node = ImmutableRequisitionNode.newBuilder()
                .setNodeLabel(cluster.name)
                .setForeignId(cluster.uuid)
                .setLocation(context.getLocation())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("alias")
                        .setValue(context.getAlias())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("name")
                        .setValue(cluster.name)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("uuid")
                        .setValue(cluster.uuid)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("type")
                        .setValue(cluster.type.name())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("isAvailable")
                        .setValue(String.valueOf(cluster.isAvailable))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("operationMode")
                        .setValue(cluster.operationMode)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("state")
                        .setValue(cluster.state)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("domainAwarenessLevel")
                        .setValue(cluster.domainAwarenessLevel)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("enabledFeatureList")
                        .setValue(cluster.enabledFeatureList.toString())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("encryptionStatus")
                        .setValue(cluster.encryptionStatus)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("serviceList")
                        .setValue(cluster.serviceList.toString())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("redundancyFactor")
                        .setValue(String.valueOf(cluster.redundancyFactor))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.isLongTermSupport")
                        .setValue(String.valueOf(cluster.build.isLongTermSupport))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.version")
                        .setValue(cluster.build.version)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.buildType")
                        .setValue(cluster.build.buildType)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.commitId")
                        .setValue(cluster.build.commitId)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.shortCommitId")
                        .setValue(cluster.build.shortCommitId)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.fullVersion")
                        .setValue(cluster.build.fullVersion)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("build.commitDate")
                        .setValue(cluster.build.commitDate.toString())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("timeZone")
                        .setValue(cluster.timeZone)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("clusterArch")
                        .setValue(cluster.clusterArch)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("ntpServerIpList")
                        .setValue(cluster.ntpServerIpList.toString())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("nameServerIpList")
                        .setValue(cluster.nameServerIpList.toString())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("externalSubnet")
                        .setValue(cluster.externalSubnet)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("externalIp")
                        .setValue(cluster.externalIp)
                        .build())
                .addInterface(
                        ImmutableRequisitionInterface.newBuilder()
                                .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(cluster.externalIp)))
                                .setDescription("Cluster Virtual IP Address")
                                .setSnmpPrimary(SnmpPrimaryType.PRIMARY)
                                .addMonitoredService("NutanixEntity")
                                .addMonitoredService("NutanixCluster")
                                .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("internalSubnet")
                        .setValue(cluster.internalSubnet)
                        .build())
                .addCategory("NutanixCluster");

        if (cluster.externalDataServicesIp != null) {
            node.addInterface(
                    ImmutableRequisitionInterface.newBuilder()
                            .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(cluster.externalDataServicesIp)))
                            .setDescription("ISCSI Data Services IP")
                            .build());
        }
        if (cluster.smtpServer.emailAddress != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.emailAddress")
                    .setValue(cluster.smtpServer.emailAddress)
                    .build());
        }
        if (cluster.smtpServer.emailAddress != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.type")
                    .setValue(cluster.smtpServer.type)
                    .build());
        }
        if (cluster.smtpServer.name != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.name")
                    .setValue(cluster.smtpServer.name)
                    .build());
        }

        if (cluster.externalDataServicesIp != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("externalDataServicesIp")
                    .setValue(cluster.externalDataServicesIp)
                    .build());
        }
        if (cluster.smtpServer.isBackup != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.isBackup")
                    .setValue(String.valueOf(cluster.smtpServer.isBackup))
                    .build());
        }
        if (cluster.smtpServer.ip != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.ip")
                    .setValue(cluster.smtpServer.ip)
                    .build());
        }
        if (cluster.smtpServer.ipv6 != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.ipv6")
                    .setValue(cluster.smtpServer.ipv6)
                    .build());
        }
        if (cluster.smtpServer.port != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.port")
                    .setValue(String.valueOf(cluster.smtpServer.port))
                    .build());
        }
        if (cluster.smtpServer.fqdn != null) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("smtpServer.fqdn")
                    .setValue(cluster.smtpServer.fqdn)
                    .build());
        }

        for (ClusterHypervisor hypervisor : cluster.nodes) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("hypervisor." + hypervisor.ip + ".ip")
                            .setValue(hypervisor.ip)
                            .build())
                    .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("hypervisor." + hypervisor.ip + ".type")
                            .setValue(hypervisor.type)
                            .build())
                    .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("hypervisor." + hypervisor.ip + ".version")
                            .setValue(hypervisor.version)
                            .build());
        }

        for (ClusterSoftware software : cluster.software) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("softwareType." + software.softwareType + ".type")
                            .setValue(software.softwareType)
                            .build())
                    .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("softwareType." + software.softwareType + ".version")
                            .setValue(software.version)
                            .build())
                    .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("softwareType." + software.softwareType + ".status")
                            .setValue(software.status)
                            .build());
        }

        for (ClusterHttpProxy httpProxy : cluster.httpProxyList) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("httpProxy." + httpProxy.name + ".name")
                            .setValue(httpProxy.name)
                            .build())
                    .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("httpProxy." + httpProxy.name + ".proxyTypeList")
                            .setValue(httpProxy.proxyTypeList.toString())
                            .build());
            if (httpProxy.isBackup != null) {
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("httpProxy." + httpProxy.name + ".isBackup")
                        .setValue(String.valueOf(httpProxy.isBackup))
                        .build());
            }
            if (httpProxy.ip != null) {
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("httpProxy." + httpProxy.name + ".ip")
                        .setValue(httpProxy.ip)
                        .build());
            }
            if (httpProxy.ipv6 != null) {
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("httpProxy." + httpProxy.name + ".ipv6")
                        .setValue(httpProxy.ipv6)
                        .build());
            }
            if (httpProxy.port != null) {
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("httpProxy." + httpProxy.name + ".port")
                        .setValue(String.valueOf(httpProxy.port))
                        .build());
            }
            if (httpProxy.fqdn != null) {
                node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("httpProxy." + httpProxy.name + ".fqdn")
                        .setValue(httpProxy.fqdn)
                        .build());
            }
        }

        for (ClusterHttpWhiteProxy httpWhiteProxy : cluster.httpProxyWhitelist) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("httpWhiteProxy." + httpWhiteProxy.target + ".target")
                    .setValue(httpWhiteProxy.target)
                    .build())
                    .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                            .setContext(NUTANIX_METADATA_CONTEXT)
                            .setKey("httpWhiteProxy." + httpWhiteProxy.target + ".targetType")
                            .setValue(httpWhiteProxy.targetType)
                            .build())
            ;
        }
            return node.build();
    }

    private RequisitionNode getHostNode(Host host, RequestContext context, String clusterName) {
        final var node = ImmutableRequisitionNode.newBuilder()
                .setNodeLabel(host.name)
                .setForeignId(host.uuid)
                .setLocation(context.getLocation())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("alias")
                        .setValue(context.getAlias())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("name")
                        .setValue(host.name)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("type")
                        .setValue(host.type.name())
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("uuid")
                        .setValue(host.uuid)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("controllerVmUuid")
                        .setValue(host.controllerVmUuid)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("specVersion")
                        .setValue(String.valueOf(host.specVersion))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("kind")
                        .setValue(host.kind)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("state")
                        .setValue(host.state)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("hypervisorFullName")
                        .setValue(host.hypervisorFullName)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("hostType")
                        .setValue(host.hostType)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("cpuModel")
                        .setValue(host.cpuModel)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("numCpuSockets")
                        .setValue(String.valueOf(host.numCpuSockets))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("numCpuCores")
                        .setValue(String.valueOf(host.numCpuCores))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("cpuCapacityHz")
                        .setValue(String.valueOf(host.cpuCapacityHz))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("serialNumber")
                        .setValue(host.serialNumber)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("memoryCapacityMib")
                        .setValue(String.valueOf(host.memoryCapacityMib))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("numVms")
                        .setValue(String.valueOf(host.numVms))
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("blockSerialNumber")
                        .setValue(host.blockSerialNumber)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("blockModel")
                        .setValue(host.blockModel)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("clusterUuid")
                        .setValue(host.clusterUuid)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("clusterName")
                        .setValue(clusterName)
                        .build())
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("clusterKind")
                        .setValue(host.clusterKind)
                        .build())
                .addCategory("NutanixHost");

        node.addAsset("category", "NutanixHost");
        node.addAsset("cpu", host.cpuModel);
        node.addAsset("ram",  host.memoryCapacityMib + "MB");
        node.addAsset("serialNumber", host.serialNumber);

        final var controllerVmIface = ImmutableRequisitionInterface.newBuilder()
                .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(host.controllerVmIp)))
                .setDescription("controllerVmIp")
                .setSnmpPrimary(SnmpPrimaryType.PRIMARY)
                .addMetaData(ImmutableRequisitionMetaData.newBuilder()
                        .setContext(NUTANIX_METADATA_CONTEXT)
                        .setKey("oplogDiskSize")
                        .setValue(String.valueOf(host.oplogDiskSize)).build())
                .addMonitoredService("NutanixControllerVm");
        node.addInterface(controllerVmIface.build());

        final var miIface = ImmutableRequisitionInterface.newBuilder()
                .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(host.ipmi)))
                .setDescription("ipmi")
                .addMonitoredService("NutanixMi");
        node.addInterface(miIface.build());

        final var hypervisorIface = ImmutableRequisitionInterface.newBuilder()
                .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(host.hypervisorIp)))
                .setDescription("hypervisorIp")
                .addMonitoredService("NutanixEntity")
                .addMonitoredService("NutanixHost");
        node.addInterface(hypervisorIface.build());
        return node.build();
    }

    private RequisitionNode getVMNode(final VM vm, RequestContext context, String clusterInternalSubnet) throws UnknownHostException {
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
                        .setKey("type")
                        .setValue(vm.type.name())
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

        if (Objects.nonNull(vm.hostName)) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("hostName")
                    .setValue(vm.hostName)
                    .build()
            );
        }
        if (Objects.nonNull(vm.hostUuid)) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("hostUuid")
                    .setValue(vm.hostUuid)
                    .build()
            );
        }
        if (Objects.nonNull(vm.description)) {
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                .setContext(NUTANIX_METADATA_CONTEXT)
                .setKey("description")
                .setValue(vm.description)
                .build()
            );
        }
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
        Set<String> ipaddresses = new HashSet<>();
        Map<String, VMNic> ipAddressToNicMap = new HashMap<>();
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
                    .setKey("nic."+nicIndex+".ipList")
                    .setValue(nic.ipList.toString())
                    .build());
            node.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("nic."+nicIndex+".isConnected")
                    .setValue(String.valueOf(nic.isConnected))
                    .build());
            nicIndex++;
            ipaddresses.addAll(nic.ipList);
            nic.ipList.forEach(ip -> ipAddressToNicMap.put(ip, nic));
        }
        boolean addVMMonitor=true;
        for (String ipAddress: ipaddresses) {
            VMNic nic = ipAddressToNicMap.get(ipAddress);
            final var iface = ImmutableRequisitionInterface.newBuilder()
                    .setIpAddress(Objects.requireNonNull(Utils.getValidInetAddress(ipAddress)));
            iface.addMetaData(ImmutableRequisitionMetaData.newBuilder()
                    .setContext(NUTANIX_METADATA_CONTEXT)
                    .setKey("nicName")
                    .setValue(nic.name).build());
            if (nic.isConnected && addVMMonitor && !Utils.isIpInSubnet(ipAddress,clusterInternalSubnet )) {
                iface.addMonitoredService("NutanixVM");
                addVMMonitor=false;
            }
            iface.addMonitoredService("NutanixEntity");
            node.addInterface(iface.build());
        }
        return node.build();
    }
    protected Requisition handleRequest(final RequestContext context) throws NutanixApiException {
        var request = (Request) context.getRequest();

        final var requisition = ImmutableRequisition.newBuilder()
                .setForeignSource(context.request.foreignSource);

        ApiClientService apiClientService = context.getClient();
        Map<String,String> clusterUuidToNameMap = new HashMap<>();
        Map<String,String> clusterUuidToInternalMap = new HashMap<>();

        for (Cluster cluster: apiClientService.getClusters()) {
            clusterUuidToNameMap.put(cluster.uuid, cluster.name);
            clusterUuidToInternalMap.put(cluster.uuid, cluster.internalSubnet);
            if (request.importClusters) {
                requisition.addNode(getClusterNode(cluster, context));
            }
        }

        if (request.importHosts) {
            for (Host host: apiClientService.getHosts()) {
                requisition.addNode(getHostNode(host,context,clusterUuidToNameMap.get(host.clusterUuid)));
            }
        }
        if (request.importVms) {
            for (VM vm: apiClientService.getVMS()) {
                if (!request.importAllVms && !vm.powerState.equalsIgnoreCase("ON"))
                    continue;
                if (request.matchVM != null && !Pattern.compile(request.matchVM).matcher(vm.name).matches())
                    continue;
                try {
                    requisition.addNode(getVMNode(vm, context, clusterUuidToInternalMap.get(vm.clusterUuid)));
                } catch (UnknownHostException e) {
                    LOG.warn("handleRequest: not imported vm:{}, cause: {}", vm.name, e.getLocalizedMessage());
                }
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

        private String matchVM;

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
            this.foreignSource = String.format("%s-%s", TYPE, this.alias);
        }


    }

    @Getter
    public class RequestContext {
        private final Request request;

        public RequestContext(final Request request) {
            this.request = Objects.requireNonNull(request);
        }

        public ApiClientService getClient() throws NutanixApiException {
            return clientManager.getClient(ApiClientCredentials.builder()
                    .withPrismUrl(this.request.prismUrl)
                    .withUsername(this.request.username)
                    .withPassword(this.request.password)
                    .withIgnoreSslCertificateValidation(this.request.ignoreSslCertificateValidation)
                    .withLength(this.request.length)
                    .build());
        }

        public String getAlias() {
            return this.request.alias;
        }

        public String getLocation() {
            return this.request.location;
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
