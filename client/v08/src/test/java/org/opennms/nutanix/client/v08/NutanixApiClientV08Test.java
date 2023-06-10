package org.opennms.nutanix.client.v08;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.v08.api.VmsApi;
import org.opennms.nutanix.client.v08.handler.ApiClient;
import org.opennms.nutanix.client.v08.handler.ApiException;
import org.opennms.nutanix.client.v08.model.GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt;

public class NutanixApiClientV08Test {

    private ApiClient getApiClient() {

        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/api/nutanix/v0.8/");
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
        System.out.println(System.getenv("NTX_USER"));
        System.out.println(System.getenv("NTX_PASS"));

    }

    @Test
    public void testVmsApi()  {

        VmsApi vmsApi = new VmsApi(getApiClient());
        GetBaseEntityCollectionltgetDtoAcropolisVMInfoDTOgt dto;
        try {
            dto = vmsApi.getVMs(false,false,false);
            System.out.println(dto);
        } catch (ApiException e) {
            System.out.println("err");
        }
    }

}
