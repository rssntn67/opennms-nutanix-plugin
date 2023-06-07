package org.opennms.nutanix.client.v08;

import java.util.HashSet;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v08.handler.ApiClient;
import org.opennms.nutanix.client.v08.api.VmsApi;
import org.opennms.nutanix.client.v08.handler.ApiException;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisVMNicSpecDTOgt;

public class NutanixApiClientV08Test {

    private ApiClient getApiClient() {
        ApiClient apiClient = new ApiClient();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/api/nutanix/v3");
        apiClient.setUsername(System.getenv("NTX_USER"));
        apiClient.setPassword(System.getenv("NTX_PASS"));
        apiClient.setDebugging(true);
        return apiClient;

    }

    @Test
    public void testEnvVariable() {
        Assert.assertNotNull(System.getenv("NTX_USER"));
        Assert.assertNotNull(System.getenv("NTX_PASS"));
    }
    @Test
    public void testVmsApi() throws NutanixApiException {

        VmsApi vmsApi = new VmsApi(getApiClient());
        GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt dto;
        try {
            dto = vmsApi.getVMs(true,true,true);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(),e);
        }
        System.out.println(dto);
    }

}
