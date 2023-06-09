package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v3.api.AlertsApi;
import org.opennms.nutanix.client.v3.api.ClustersApi;
import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.api.VmsApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.AlertIntentResource;
import org.opennms.nutanix.client.v3.model.AlertListIntentResponse;
import org.opennms.nutanix.client.v3.model.AlertListMetadata;
import org.opennms.nutanix.client.v3.model.ClusterIntentResource;
import org.opennms.nutanix.client.v3.model.ClusterIntentResponse;
import org.opennms.nutanix.client.v3.model.ClusterListIntentResponse;
import org.opennms.nutanix.client.v3.model.ClusterListMetadata;
import org.opennms.nutanix.client.v3.model.HostIntentResource;
import org.opennms.nutanix.client.v3.model.HostIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;
import org.opennms.nutanix.client.v3.model.VmIntentResource;
import org.opennms.nutanix.client.v3.model.VmIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;

public class NutanixV3ApiClient implements NutanixApiClient {

    private final ApiClientExtention apiClient;

    public NutanixV3ApiClient(ApiClientExtention apiClient) {
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
                .withUuid(vmIntentResponse.getMetadata().getUuid())
                .build();
    }

    private static VM getFromVmIntentResource(VmIntentResource vmIntentResource) {
        return VM.builder()
                .withName(vmIntentResource.getStatus().getName())
                .withUuid(vmIntentResource.getMetadata().getUuid())
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
                .build();
    }

    private Host getFromHostIntentResponse(HostIntentResponse hostIntenthostIntentResponseurce) {
        return Host.builder()
                .withName(hostIntenthostIntentResponseurce.getStatus().getName())
                .withUuid(hostIntenthostIntentResponseurce.getMetadata().getUuid())
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
                .withName(cluster.getStatus().getName())
                .withUuid(cluster.getMetadata().getUuid())
                .withOperationMode(cluster.getStatus().getResources().getConfig().getOperationMode())
                .withIsAvailable(cluster.getStatus().getResources().getConfig().isIsAvailable())
                .build();
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
    public MetricsCluster getClusterMetric(String uuid) {
        return null;
    }

    private Alert getFromAlertIntentResource(AlertIntentResource alert) {
        return Alert.builder().withUuid(alert.getMetadata().getUuid()).withSeverity(alert.getStatus().getResources().getSeverity()).withType(alert.getStatus().getResources().getType()).build();
    }


}
