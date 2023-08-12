package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.ClusterBuildInfo;
import org.opennms.nutanix.client.api.model.ClusterHttpProxy;
import org.opennms.nutanix.client.api.model.ClusterHttpWhiteProxy;
import org.opennms.nutanix.client.api.model.ClusterSmtpServer;
import org.opennms.nutanix.client.api.model.ClusterSoftware;
import org.opennms.nutanix.client.api.model.VMDisk;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.ClusterHypervisor;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VMNic;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v3.api.AlertsApi;
import org.opennms.nutanix.client.v3.api.ClustersApi;
import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.api.VmsApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.AlertIntentResource;
import org.opennms.nutanix.client.v3.model.AlertListIntentResponse;
import org.opennms.nutanix.client.v3.model.AlertListMetadata;
import org.opennms.nutanix.client.v3.model.BuildInfo;
import org.opennms.nutanix.client.v3.model.ClusterIntentResource;
import org.opennms.nutanix.client.v3.model.ClusterIntentResponse;
import org.opennms.nutanix.client.v3.model.ClusterListIntentResponse;
import org.opennms.nutanix.client.v3.model.ClusterListMetadata;
import org.opennms.nutanix.client.v3.model.ClusterNetworkEntity;
import org.opennms.nutanix.client.v3.model.ClusterNodes;
import org.opennms.nutanix.client.v3.model.HostIntentResource;
import org.opennms.nutanix.client.v3.model.HostIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;
import org.opennms.nutanix.client.v3.model.HttpProxyWhitelist;
import org.opennms.nutanix.client.v3.model.IpAddress;
import org.opennms.nutanix.client.v3.model.Reference;
import org.opennms.nutanix.client.v3.model.SmtpServer;
import org.opennms.nutanix.client.v3.model.VmDiskOutputStatus;
import org.opennms.nutanix.client.v3.model.VmIntentResource;
import org.opennms.nutanix.client.v3.model.VmIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;
import org.opennms.nutanix.client.v3.model.VmNicOutputStatus;

public class V3ApiClientService implements ApiClientService {
    private final ApiClientExtention apiClient;
    public V3ApiClientService(ApiClientExtention apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public List<VM> getVMS() throws NutanixApiException {
        VmsApi vmsApi = new VmsApi(apiClient);
        List<VM> vms = new ArrayList<>();
        int offset = 0;
        int total;
        do {
            VmListMetadata body = new VmListMetadata().length(apiClient.getLength()).offset(offset);
            try {
                VmListIntentResponse vmListIntentResponse = vmsApi.vmsListPost(body);

                total = vmListIntentResponse.getMetadata().getTotalMatches();
                vmListIntentResponse.getEntities().forEach(vm -> vms.add(getFromVmIntentResource(vm)));
                offset+=vmListIntentResponse.getEntities().size();
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
        } while (vms.size() < total );
        return vms;
    }

    @Override
    public VM getVM(String uuid) throws NutanixApiException {
        VmsApi vmsApi = new VmsApi(apiClient);
        VmIntentResponse vmIntentResponse;
        try {
            vmIntentResponse = vmsApi.vmsUuidGet(uuid);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        return getFromVmIntentResponse(vmIntentResponse);
    }

    private static VM getFromVmIntentResponse(VmIntentResponse vmIntentResponse) {
        return VM.builder()
                .withName(vmIntentResponse.getStatus().getName())
                .withDescription(vmIntentResponse.getStatus().getDescription())
                .withUuid(vmIntentResponse.getMetadata().getUuid())
                .withClusterName(vmIntentResponse.getStatus().getClusterReference().getName())
                .withClusterUuid(vmIntentResponse.getStatus().getClusterReference().getUuid())
                .withHostName(getHostName(vmIntentResponse.getStatus().getResources().getHostReference()))
                .withHostUuid(getHostUuid(vmIntentResponse.getStatus().getResources().getHostReference()))
                .withState(vmIntentResponse.getStatus().getState())
                .withNumThreadsPerCore(vmIntentResponse.getStatus().getResources().getNumThreadsPerCore())
                .withMemorySizeMib(vmIntentResponse.getStatus().getResources().getMemorySizeMib())
                .withPowerState(vmIntentResponse.getStatus().getResources().getPowerState())
                .withNumVcpusPerSocket(vmIntentResponse.getStatus().getResources().getNumVcpusPerSocket())
                .withNumSockets(vmIntentResponse.getStatus().getResources().getNumSockets())
                .withProtectionType(vmIntentResponse.getStatus().getResources().getProtectionType())
                .withMachineType(vmIntentResponse.getStatus().getResources().getMachineType())
                .withHypervisorType(vmIntentResponse.getStatus().getResources().getHypervisorType())
                .withDisks(getDisksFromVmResources(vmIntentResponse.getStatus().getResources().getDiskList()))
                .withNics(getNicListFromVmResources(vmIntentResponse.getStatus().getResources().getNicList()))
                .build();
    }

    private static List<VMNic> getNicListFromVmResources(List<VmNicOutputStatus> vmNicOutputStatusList) {
        return vmNicOutputStatusList
                .stream()
                .map(n -> VMNic.builder()
                        .withNicType(n.getNicType())
                        .withKind(n.getSubnetReference().getKind())
                        .withName(n.getSubnetReference().getName())
                        .withMacAddress(n.getMacAddress())
                        .withVlanMode(n.getVlanMode())
                        .withIsConnected(n.isIsConnected())
                        .withIpList(n.getIpEndpointList().stream().map(IpAddress::getIp).collect(Collectors.toUnmodifiableSet()))
                        .build()
                )
                .collect(Collectors.toUnmodifiableList());

    }

    private static List<VMDisk> getDisksFromVmResources(List<VmDiskOutputStatus> vmDiskOutputStatusList) {
    return vmDiskOutputStatusList
            .stream()
            .filter(d -> d.getDeviceProperties().getDeviceType().equalsIgnoreCase("DISK"))
            .map(d -> VMDisk.builder()
                    .withUuid(d.getUuid())
                    .withDeviceType(d.getDeviceProperties().getDeviceType())
                    .withDeviceIndex(d.getDeviceProperties().getDiskAddress().getDeviceIndex())
                    .withAdapterType(d.getDeviceProperties().getDiskAddress().getAdapterType())
                    .withDiskSizeMib(d.getDiskSizeMib()).build())
            .collect(Collectors.toUnmodifiableList());
}

    private static String getHostName(Reference hostReference) {
        var hostName = (hostReference == null)  ?  null :  hostReference.getName();
        return hostName;
    }

    private static String getHostUuid(Reference hostReference) {
        var hostUuid = (hostReference == null)  ?  null :  hostReference.getUuid();
        return hostUuid;
    }

    private static VM getFromVmIntentResource(VmIntentResource vmIntentResource) {
        return VM.builder()
                .withName(vmIntentResource.getStatus().getName())
                .withDescription(vmIntentResource.getStatus().getDescription())
                .withUuid(vmIntentResource.getMetadata().getUuid())
                .withClusterName(vmIntentResource.getStatus().getClusterReference().getName())
                .withClusterUuid(vmIntentResource.getStatus().getClusterReference().getUuid())
                .withHostName(getHostName(vmIntentResource.getStatus().getResources().getHostReference()))
                .withHostUuid(getHostUuid(vmIntentResource.getStatus().getResources().getHostReference()))
                .withState(vmIntentResource.getStatus().getState())
                .withNumThreadsPerCore(vmIntentResource.getStatus().getResources().getNumThreadsPerCore())
                .withMemorySizeMib(vmIntentResource.getStatus().getResources().getMemorySizeMib())
                .withPowerState(vmIntentResource.getStatus().getResources().getPowerState())
                .withNumVcpusPerSocket(vmIntentResource.getStatus().getResources().getNumVcpusPerSocket())
                .withNumSockets(vmIntentResource.getStatus().getResources().getNumSockets())
                .withProtectionType(vmIntentResource.getStatus().getResources().getProtectionType())
                .withMachineType(vmIntentResource.getStatus().getResources().getMachineType())
                .withHypervisorType(vmIntentResource.getStatus().getResources().getHypervisorType())
                .withDisks(getDisksFromVmResources(vmIntentResource.getStatus().getResources().getDiskList()))
                .withNics(getNicListFromVmResources(vmIntentResource.getStatus().getResources().getNicList()))
                .withKind(vmIntentResource.getMetadata().getKind())
                .withSpecVersion(vmIntentResource.getMetadata().getSpecVersion())
                .withEntityVersion(vmIntentResource.getMetadata().getEntityVersion())
                .build();
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        HostsApi hostsApi = new HostsApi(apiClient);
        List<Host> hosts = new ArrayList<>();
        int offset = 0;
        int total;
        do {
            HostListMetadata body = new HostListMetadata().length(apiClient.getLength()).offset(offset);
            try {
                HostListIntentResponse hostListIntentResponse = hostsApi.hostsListPost(body);

                total = hostListIntentResponse.getMetadata().getTotalMatches();
                hostListIntentResponse.getEntities().forEach(host -> hosts.add(getFromHostIntentResource(host)));
                offset+= hostListIntentResponse.getEntities().size();
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
        } while (hosts.size() < total );

        return hosts;
    }

    @Override
    public Host getHost(String uuid) throws NutanixApiException {
        HostsApi hostsApi = new HostsApi(apiClient);
        HostIntentResponse hostIntentResponse;
        try {
            hostIntentResponse = hostsApi.hostsUuidGet(uuid);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        return getFromHostIntentResponse(hostIntentResponse);
    }

    private Host getFromHostIntentResource(HostIntentResource hostIntentResource) {
        return Host.builder()
                .withName(hostIntentResource.getStatus().getName())
                .withUuid(hostIntentResource.getMetadata().getUuid())
                .withSpecVersion(hostIntentResource.getMetadata().getSpecVersion())
                .withKind(hostIntentResource.getMetadata().getKind())
                .withState(hostIntentResource.getStatus().getState())
                .withControllerVmIp(hostIntentResource.getStatus().getResources().getControllerVm().getIp())
                .withOplogDiskPct(hostIntentResource.getStatus().getResources().getControllerVm().getOplogUsage().getOplogDiskPct())
                .withOplogDiskSize(hostIntentResource.getStatus().getResources().getControllerVm().getOplogUsage().getOplogDiskSize())
                .withIpmi(hostIntentResource.getStatus().getResources().getIpmi().getIp())
                .withHostType(hostIntentResource.getStatus().getResources().getHostType())
                .withCpuModel(hostIntentResource.getStatus().getResources().getCpuModel())
                .withNumCpuSockets(hostIntentResource.getStatus().getResources().getNumCpuSockets())
                .withNumCpuCores(hostIntentResource.getStatus().getResources().getNumCpuCores())
                .withCpuCapacityHz(hostIntentResource.getStatus().getResources().getCpuCapacityHz())
                .withSerialNumber(hostIntentResource.getStatus().getResources().getSerialNumber())
                .withMemoryCapacityMib(hostIntentResource.getStatus().getResources().getMemoryCapacityMib())
                .withNumVms(hostIntentResource.getStatus().getResources().getHypervisor().getNumVms())
                .withHypervisorIp(hostIntentResource.getStatus().getResources().getHypervisor().getIp())
                .withHypervisorFullName(hostIntentResource.getStatus().getResources().getHypervisor().getHypervisorFullName())
                .withBlockSerialNumber(hostIntentResource.getStatus().getResources().getBlock().getBlockSerialNumber())
                .withBlockModel(hostIntentResource.getStatus().getResources().getBlock().getBlockModel())
                .withClusterKind(hostIntentResource.getStatus().getClusterReference().getKind())
                .withClusterUuid(hostIntentResource.getStatus().getClusterReference().getUuid())
                .build();
    }

    private Host getFromHostIntentResponse(HostIntentResponse hostIntentResource) {
        return Host.builder()
                .withName(hostIntentResource.getStatus().getName())
                .withUuid(hostIntentResource.getMetadata().getUuid())
                .withSpecVersion(hostIntentResource.getMetadata().getSpecVersion())
                .withKind(hostIntentResource.getMetadata().getKind())
                .withState(hostIntentResource.getStatus().getState())
                .withControllerVmIp(hostIntentResource.getStatus().getResources().getControllerVm().getIp())
                .withOplogDiskPct(hostIntentResource.getStatus().getResources().getControllerVm().getOplogUsage().getOplogDiskPct())
                .withOplogDiskSize(hostIntentResource.getStatus().getResources().getControllerVm().getOplogUsage().getOplogDiskSize())
                .withIpmi(hostIntentResource.getStatus().getResources().getIpmi().getIp())
                .withHostType(hostIntentResource.getStatus().getResources().getHostType())
                .withCpuModel(hostIntentResource.getStatus().getResources().getCpuModel())
                .withNumCpuSockets(hostIntentResource.getStatus().getResources().getNumCpuSockets())
                .withNumCpuCores(hostIntentResource.getStatus().getResources().getNumCpuCores())
                .withCpuCapacityHz(hostIntentResource.getStatus().getResources().getCpuCapacityHz())
                .withSerialNumber(hostIntentResource.getStatus().getResources().getSerialNumber())
                .withMemoryCapacityMib(hostIntentResource.getStatus().getResources().getMemoryCapacityMib())
                .withNumVms(hostIntentResource.getStatus().getResources().getHypervisor().getNumVms())
                .withHypervisorIp(hostIntentResource.getStatus().getResources().getHypervisor().getIp())
                .withHypervisorFullName(hostIntentResource.getStatus().getResources().getHypervisor().getHypervisorFullName())
                .withBlockSerialNumber(hostIntentResource.getStatus().getResources().getBlock().getBlockSerialNumber())
                .withBlockModel(hostIntentResource.getStatus().getResources().getBlock().getBlockModel())
                .withClusterKind(hostIntentResource.getStatus().getClusterReference().getKind())
                .withClusterUuid(hostIntentResource.getStatus().getClusterReference().getUuid())
                .build();
    }

    @Override
    public List<Cluster> getClusters() throws NutanixApiException {
        ClustersApi clustersApi = new ClustersApi(apiClient);
        List<Cluster> clusters = new ArrayList<>();
        int offset = 0;
        int total;
        do {
            ClusterListMetadata body = new ClusterListMetadata().length(apiClient.getLength()).offset(offset);
            try {
                ClusterListIntentResponse clustersListIntentResponse = clustersApi.clustersListPost(body);

                total = clustersListIntentResponse.getMetadata().getTotalMatches();
                clustersListIntentResponse.getEntities().forEach(cluster -> clusters.add(getFromClusterIntentResource(cluster)));
                offset+=clustersListIntentResponse.getEntities().size();
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage());
            }
        } while (clusters.size() < total );
        return clusters;
    }

    @Override
    public Cluster getCluster(String uuid) throws NutanixApiException {
        ClustersApi clustersApi = new ClustersApi(apiClient);
        ClusterIntentResponse clusterIntentResponse;
        try {
            clusterIntentResponse = clustersApi.clustersUuidGet(uuid);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        return getFromClusterIntentResponse(clusterIntentResponse);
    }

    private Cluster getFromClusterIntentResource(ClusterIntentResource cluster) {
        return Cluster.builder()
                .withUuid(cluster.getMetadata().getUuid())
                .withState(cluster.getStatus().getState())
                .withName(cluster.getStatus().getName())
                .withNodes(getFromClusterNodes(cluster.getStatus().getResources().getNodes()))
                .withSoftware(getFromSoftwareMap(cluster.getStatus().getResources().getConfig().getSoftwareMap()))
                .withEncryptionStatus(cluster.getStatus().getResources().getConfig().getEncryptionStatus())
                .withServiceList(cluster.getStatus().getResources().getConfig().getServiceList())
                .withRedundancyFactor(cluster.getStatus().getResources().getConfig().getRedundancyFactor())
                .withOperationMode(cluster.getStatus().getResources().getConfig().getOperationMode())
                .withDomainAwarenessLevel(cluster.getStatus().getResources().getConfig().getDomainAwarenessLevel())
                .withEnabledFeatureList(cluster.getStatus().getResources().getConfig().getEnabledFeatureList())
                .withIsAvailable(cluster.getStatus().getResources().getConfig().isIsAvailable())
                .withBuild(getFromClusterBuild(cluster.getStatus().getResources().getConfig().getBuild()))
                .withTimeZone(cluster.getStatus().getResources().getConfig().getTimezone())
                .withClusterArch(cluster.getStatus().getResources().getConfig().getClusterArch())
                .withExternalIp(cluster.getStatus().getResources().getNetwork().getExternalIp())
                .withHttpProxyList(getFromClusterHttpProxyList(cluster.getStatus().getResources().getNetwork().getHttpProxyList()))
                .withSmtpServer(getFromClusterSmtpServer(cluster.getStatus().getResources().getNetwork().getSmtpServer()))
                .withNtpServerIpList(cluster.getStatus().getResources().getNetwork().getNtpServerIpList())
                .withExternalSubnet(cluster.getStatus().getResources().getNetwork().getExternalSubnet())
                .withExternalDataServicesIp(cluster.getStatus().getResources().getNetwork().getExternalDataServicesIp())
                .withNameServerIpList(cluster.getStatus().getResources().getNetwork().getNameServerIpList())
                .withHttpProxyWhitelist(getFromClusterWhiteProxyList(cluster.getStatus().getResources().getNetwork().getHttpProxyWhitelist()))
                .withInternalSubnet(cluster.getStatus().getResources().getNetwork().getInternalSubnet())
                .build();
    }

    private List<ClusterHttpWhiteProxy> getFromClusterWhiteProxyList(List<HttpProxyWhitelist> httpProxyWhitelist) {
        return
                httpProxyWhitelist.stream()
                        .map(h -> ClusterHttpWhiteProxy
                                .builder()
                                .withTarget(h.getTarget())
                                .withTargetType(h.getTargetType())
                                .build())
                        .collect(Collectors.toUnmodifiableList());
    }

    private ClusterSmtpServer getFromClusterSmtpServer(SmtpServer smtpServer) {
        return ClusterSmtpServer.builder()
                .withType(smtpServer.getType())
                .withEmailAddress(smtpServer.getEmailAddress())
                .withIp(smtpServer.getServer().getAddress().getIp())
                .withFqdn(smtpServer.getServer().getAddress().getFqdn())
                .withIsBackup(smtpServer.getServer().getAddress().isIsBackup())
                .withPort(smtpServer.getServer().getAddress().getPort())
                .withIpv6(smtpServer.getServer().getAddress().getIpv6())
                .build();
    }

    private List<ClusterHttpProxy> getFromClusterHttpProxyList(List<ClusterNetworkEntity> httpProxyList) {
        return
                httpProxyList
                        .stream()
                        .map(p -> ClusterHttpProxy.builder()
                                .withName(p.getName())
                                .withProxyTypeList(p.getProxyTypeList())
                                .withIp(p.getAddress().getIp())
                                .withFqdn(p.getAddress().getFqdn())
                                .withIsBackup(p.getAddress().isIsBackup())
                                .withPort(p.getAddress().getPort())
                                .withIpv6(p.getAddress().getIpv6())
                                .build())
                        .collect(Collectors.toUnmodifiableList());
    }

    private ClusterBuildInfo getFromClusterBuild(BuildInfo build) {
        return ClusterBuildInfo
                .builder()
                .withBuildType(build.getBuildType())
                .withCommitDate(build.getCommitDate())
                .withCommitId(build.getCommitId())
                .withFullVersion(build.getFullVersion())
                .withVersion(build.getVersion())
                .withIsLongTermSupport(build.isIsLongTermSupport())
                .build();
    }

    private List<ClusterSoftware> getFromSoftwareMap(Map<String,org.opennms.nutanix.client.v3.model.ClusterSoftware> softwareMap) {
        return softwareMap.values().stream().map(s ->
                ClusterSoftware
                        .builder()
                        .withSoftwareType(s.getSoftwareType())
                        .withStatus(s.getStatus())
                        .withVersion(s.getVersion())
                        .build())
                .collect(Collectors.toUnmodifiableList());
    }

    private List<ClusterHypervisor> getFromClusterNodes(ClusterNodes nodes) {
        return nodes.getHypervisorServerList().stream().map(h -> ClusterHypervisor.builder()
                .withType(h.getType())
                .withIp(h.getIp())
                .withVersion(h.getVersion())
                .build()).collect(Collectors.toUnmodifiableList());
    }

    private Cluster getFromClusterIntentResponse(ClusterIntentResponse cluster) {
        return Cluster.builder()
                .withName(cluster.getStatus().getName())
                .withUuid(cluster.getMetadata().getUuid())
                .withOperationMode(cluster.getStatus().getResources().getConfig().getOperationMode())
                .withIsAvailable(cluster.getStatus().getResources().getConfig().isIsAvailable())
                .build();
    }

    @Override
    public List<Alert> getAlerts() throws NutanixApiException {
        AlertsApi alertsApi = new AlertsApi(apiClient);
        List<Alert> alerts = new ArrayList<>();
        int offset = 0;
        int total;
        do {
            AlertListMetadata body = new AlertListMetadata().length(apiClient.getLength()).offset(offset);
            try {
                AlertListIntentResponse alertsListIntentResponse = alertsApi.alertsListPost(body);

                total = alertsListIntentResponse.getMetadata().getTotalMatches();
                alertsListIntentResponse.getEntities().forEach(alert -> alerts.add(getFromAlertIntentResource(alert)));
                offset+=alertsListIntentResponse.getEntities().size();
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage());
            }
        } while (alerts.size() < total );
        return alerts;    }

    @Override
    public MetricsCluster getClusterMetric(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");

    }

    private Alert getFromAlertIntentResource(AlertIntentResource alert) {
        return Alert.builder().withUuid(alert.getMetadata().getUuid()).withSeverity(alert.getStatus().getResources().getSeverity()).withType(alert.getStatus().getResources().getType()).build();
    }


}
