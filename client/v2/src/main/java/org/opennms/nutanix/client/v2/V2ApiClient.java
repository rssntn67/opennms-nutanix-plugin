package org.opennms.nutanix.client.v2;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.ApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v2.api.VmsApi;
import org.opennms.nutanix.client.v2.handler.ApiException;
import org.opennms.nutanix.client.v2.model.GetBaseEntityCollectionltgetDtoUhuraVmConfigDTOgt;
import org.opennms.nutanix.client.v2.model.GetDtoUhuraVmConfigDTO;

public class V2ApiClient implements ApiClient {

    private final ApiClientExtention apiClient;

    public V2ApiClient(ApiClientExtention apiClient) {
        this.apiClient = apiClient;
    }


    @Override
    public List<VM> getVMS() throws NutanixApiException {
        VmsApi vmsApi= new VmsApi(apiClient);
        List<VM> vms = new ArrayList<>();
        int total;
        int endIndex;
        int startIndex = 0;
        do {
            try {
                GetBaseEntityCollectionltgetDtoUhuraVmConfigDTOgt dto = vmsApi.getVMs(null, startIndex, apiClient.getLength(), null,null,true, true);
                dto.getEntities().forEach(vm -> vms.add(getFromDtoUhuraVmConfigDTO(vm)));
                endIndex=dto.getMetadata().getEndIndex();
                total = dto.getMetadata().getGrandTotalEntities();
                startIndex = endIndex;
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
        } while (endIndex < total );

        return vms;
    }

    private VM getFromDtoUhuraVmConfigDTO(GetDtoUhuraVmConfigDTO vmdto) {
        return VM.builder()
                .withName(vmdto.getName())
                .withUuid(vmdto.getUuid())
                .build();
    }
    @Override
    public VM getVM(String uuid) throws NutanixApiException {
        VmsApi vmsApi= new VmsApi(apiClient);
        GetDtoUhuraVmConfigDTO dto;
        try {
            dto = vmsApi.getVM(uuid,true,true);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        return getFromDtoUhuraVmConfigDTO(dto);
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        throw new NutanixApiException("getHosts() not supported");
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
    public MetricsCluster getClusterMetric(String uuid) throws NutanixApiException{
        throw new NutanixApiException("not supported");
    }


}
