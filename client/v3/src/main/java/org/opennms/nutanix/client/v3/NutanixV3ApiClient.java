package org.opennms.nutanix.client.v3;

import java.util.ArrayList;
import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.api.VmsApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
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
        int length = apiClient.getLength();
        int total;
        do {
            VmListMetadata body = new VmListMetadata().length(length).offset(offset);
            try {
                VmListIntentResponse vmListIntentResponse = vmsApi.vmsListPost(body);
                total = vmListIntentResponse.getMetadata().getTotalMatches();

                vmListIntentResponse.getEntities().forEach(vm -> vms.add(getFromVmIntentResource(vm)));
                length = vmListIntentResponse.getEntities().size();
                offset+=length;
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
        int length = apiClient.getLength();
        int total;
        do {
            HostListMetadata body = new HostListMetadata().length(length).offset(offset);
            try {
                HostListIntentResponse hostListIntentResponse = hostsApi.hostsListPost(body);
                total = hostListIntentResponse.getMetadata().getTotalMatches();

                hostListIntentResponse.getEntities().forEach(host -> hosts.add(getFromHostIntentResource(host)));
                length = hostListIntentResponse.getEntities().size();
                offset+=length;
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage());
            }
        } while (hosts.size() < total );

        return hosts;
    }

    private Host getFromHostIntentResource(HostIntentResource hostIntentResource) {
        return Host.builder().withName(hostIntentResource.getStatus().getName()).withUuid(hostIntentResource.getMetadata().getUuid()).build();
    }
}
