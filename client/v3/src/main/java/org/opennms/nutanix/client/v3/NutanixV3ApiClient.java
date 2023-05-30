package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
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
import org.opennms.nutanix.client.v3.model.ClusterListIntentResponse;
import org.opennms.nutanix.client.v3.model.ClusterListMetadata;
import org.opennms.nutanix.client.v3.model.HostIntentResource;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;
import org.opennms.nutanix.client.v3.model.VmIntentResource;
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
                throw new NutanixApiException(e.getMessage());
            }
        } while (vms.size() < total );

        return vms;
    }

    private static VM getFromVmIntentResource(VmIntentResource vmIntentResource) {
        return VM.builder().withName(vmIntentResource.getStatus().getName()).withUuid(vmIntentResource.getMetadata().getUuid()).build();
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
                throw new NutanixApiException(e.getMessage());
            }
        } while (hosts.size() < total );

        return hosts;
    }

    private Host getFromHostIntentResource(HostIntentResource hostIntentResource) {
        return Host.builder().withName(hostIntentResource.getStatus().getName()).withUuid(hostIntentResource.getMetadata().getUuid()).build();
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

    private Cluster getFromClusterIntentResource(ClusterIntentResource cluster) {
        return Cluster.builder().withName(cluster.getStatus().getName()).withUuid(cluster.getMetadata().getUuid()).build();
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

    private Alert getFromAlertIntentResource(AlertIntentResource alert) {
        return Alert.builder().withUuid(alert.getMetadata().getUuid()).withSeverity(alert.getStatus().getResources().getSeverity()).withType(alert.getStatus().getResources().getType()).build();
    }


}
