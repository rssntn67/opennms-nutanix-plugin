package org.opennms.nutanix.client.v3;

import org.junit.Test;
import org.opennms.nutanix.client.v3.api.HostsApi;
import org.opennms.nutanix.client.v3.api.VmsApi;
import org.opennms.nutanix.client.v3.handler.ApiClient;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.HostListIntentResponse;
import org.opennms.nutanix.client.v3.model.HostListMetadata;
import org.opennms.nutanix.client.v3.model.VmListIntentResponse;
import org.opennms.nutanix.client.v3.model.VmListMetadata;

public class NutanixApiClientV3Test {

    @Test
    public void testVmsApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("https://your-nutanix-cluster-ip/api/v3");
        apiClient.setUsername("nutanixAdmin");
        apiClient.setPassword("nutanixPass");
        apiClient.setDebugging(true);

        VmsApi vmsApi = new VmsApi(apiClient);

        try {

            VmListIntentResponse vmListIntentResponse = vmsApi.vmsListPost(new VmListMetadata().kind("vm").length(10).offset(0));
            vmListIntentResponse.getEntities().forEach(vm -> System.out.println(vm.getSpec().getDescription()));

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testHostApi() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("https://your-nutanix-cluster-ip/api/v3");
        apiClient.setUsername("nutanixAdmin");
        apiClient.setPassword("nutanixPass");
        apiClient.setDebugging(true);

        HostsApi hostsApi = new HostsApi(apiClient);

        try {

            HostListIntentResponse hostListIntentResponse = hostsApi.hostsListPost(new HostListMetadata().kind("host").length(10).offset(0));
            hostListIntentResponse.getEntities().forEach(h -> System.out.println(h.getSpec().getName()));

        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

    }


}
