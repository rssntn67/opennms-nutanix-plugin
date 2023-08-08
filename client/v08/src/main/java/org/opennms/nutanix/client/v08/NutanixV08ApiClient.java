package org.opennms.nutanix.client.v08;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v08.api.VmsApi;
import org.opennms.nutanix.client.v08.handler.ApiException;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt;
import org.opennms.nutanix.client.v08.model.GetDtoAcropolisVMInfoDTO;

public class NutanixV08ApiClient implements NutanixApiClient {


    private final ApiClientExtention apiClient;

    public NutanixV08ApiClient(ApiClientExtention apiClient) {
        this.apiClient = apiClient;
    }

    @Override
    public List<VM> getVMS() throws NutanixApiException {
        VmsApi vmsApi = new VmsApi(apiClient);
        List<VM> vms = new ArrayList<>();

        try {
            GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt vmsdto = vmsApi.getVMs(true,true,true);
            vmsdto.getEntities().forEach(vmdto -> vms.add(getFromVmAcropolisDto(vmdto)));
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        return vms;
    }

    private VM getFromVmAcropolisDto(GetDtoAcropolisVMInfoDTO vmdto) {
        return VM.builder()
                .withName(vmdto.getConfig().getName())
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
