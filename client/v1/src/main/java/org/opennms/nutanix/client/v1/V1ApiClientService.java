package org.opennms.nutanix.client.v1;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v1.api.VmsApi;
import org.opennms.nutanix.client.v1.handler.ApiException;
import org.opennms.nutanix.client.v1.model.VMEntity;
import org.opennms.nutanix.client.v1.model.VMCollectionEntity;

public class V1ApiClientService implements ApiClientService {


    private final ApiClientExtention apiClient;

    public V1ApiClientService(ApiClientExtention apiClient) {
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
                VMCollectionEntity dto = vmsApi.getVMs(page, apiClient.getLength());
                dto.getEntities().forEach(vm -> vms.add(getFromVmEntity(vm)));
                endIndex=dto.getMetadata().getEndIndex();
                total = dto.getMetadata().getGrandTotalEntities();
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
        } while (endIndex < total );

        return vms;
    }

    private VM getFromVmEntity(VMEntity vmdto) {
        return VM.builder()
                .withName(vmdto.getVmName())
                .withUuid(vmdto.getUuid())
                .build();
    }

    @Override
    public VM getVM(String uuid) throws NutanixApiException {
        VmsApi vmsApi= new VmsApi(apiClient);
        VMEntity vmEntity;
        try {
            vmEntity = vmsApi.getVM(uuid);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        return getFromVmEntity(vmEntity);
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        throw new NutanixApiException("not supported", new UnsupportedOperationException());
    }

    @Override
    public Host getHost(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported", new UnsupportedOperationException());
    }

    @Override
    public List<Cluster> getClusters() throws NutanixApiException {
        throw new NutanixApiException("not supported", new UnsupportedOperationException());
    }

    @Override
    public Cluster getCluster(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported", new UnsupportedOperationException());
    }

    @Override
    public List<Alert> getAlerts() throws NutanixApiException {
        throw new NutanixApiException("not supported", new UnsupportedOperationException());

    }

    @Override
    public MetricsCluster getClusterMetric(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported", new UnsupportedOperationException());
    }


}
