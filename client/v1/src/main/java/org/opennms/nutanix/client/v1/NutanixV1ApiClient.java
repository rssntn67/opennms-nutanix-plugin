package org.opennms.nutanix.client.v1;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v1.api.VmsApi;
import org.opennms.nutanix.client.v1.handler.ApiException;
import org.opennms.nutanix.client.v1.model.Entity;
import org.opennms.nutanix.client.v1.model.VMs;

public class NutanixV1ApiClient implements NutanixApiClient {


    private final ApiClientExtention apiClient;

    public NutanixV1ApiClient(ApiClientExtention apiClient) {
        this.apiClient = apiClient;
    }
    @Override
    public List<VM> getVMS() throws NutanixApiException {
        VmsApi vmsApi= new VmsApi(apiClient);
        List<VM> vms = new ArrayList<>();
        int page = 1;
        int endIndex;
        int total;
        do {
            try {
                VMs dto = vmsApi.getVMs(page, apiClient.getLength());
                dto.getEntities().forEach(vm -> vms.add(getFromVmEntity(vm)));
                endIndex=dto.getMetadata().getEndIndex();
                total = dto.getMetadata().getGrandTotalEntities();
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
        } while (endIndex < total );

        return vms;
    }

    private VM getFromVmEntity(Entity vmdto) {
        return VM.builder()
                .withName(vmdto.getVmName())
                .withUuid(vmdto.getUuid())
                .build();
    }

    @Override
    public VM getVM(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public Host getHost(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public List<Cluster> getClusters() throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public Cluster getCluster(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public List<Alert> getAlerts() throws NutanixApiException {
        throw new NutanixApiException("not supported");

    }

    @Override
    public MetricsCluster getClusterMetric(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }


}
