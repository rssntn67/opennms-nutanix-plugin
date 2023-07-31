package org.opennms.nutanix.client.v2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v2.api.VmsApi;
import org.opennms.nutanix.client.v2.handler.ApiClient;
import org.opennms.nutanix.client.v2.handler.ApiException;
import org.opennms.nutanix.client.v2.model.GetBaseEntityCollectionltgetDtoUhuraVmConfigDTOgt;
import org.opennms.nutanix.client.v2.model.GetDtoUhuraVmConfigDTO;

public class NutanixApiClientV2Test {

    private ApiClient getApiClient() {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/PrismGateway/services/rest/v2.0");
        String auth = System.getenv("NTX_USER") + ":" + System.getenv("NTX_PASS");
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setDebugging(true);
        apiClient.setIgnoreSslCertificateValidation(true);
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
        GetBaseEntityCollectionltgetDtoUhuraVmConfigDTOgt dto;
        try {
            dto = vmsApi.getVMs(null, 0, 2, null,null,true, true);
                    } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        System.out.println(dto.getMetadata());
        dto.getEntities().forEach(vm -> System.out.println(vm));

    }

}
