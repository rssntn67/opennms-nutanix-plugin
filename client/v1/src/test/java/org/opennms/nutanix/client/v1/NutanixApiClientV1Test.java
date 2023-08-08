package org.opennms.nutanix.client.v1;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v1.api.AuthconfigApi;
import org.opennms.nutanix.client.v1.handler.ApiClient;
import org.opennms.nutanix.client.v1.handler.ApiException;
import org.opennms.nutanix.client.v1.handler.Pair;
import org.opennms.nutanix.client.v1.model.GetAuthDtoConfigAuthConfigDTO;

public class NutanixApiClientV1Test {


    private ApiClient getApiClient() {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/PrismGateway/services/rest/v1");
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
        ApiClient apiClient = getApiClient();

        Object localVarPostBody = null;
        // create path and map variables
        String localVarPath = "/vms/";

        // query params
        List<Pair> localVarQueryParams = new ArrayList<Pair>();
        Map<String, String> localVarHeaderParams = new HashMap<String, String>();
        Map<String, Object> localVarFormParams = new HashMap<String, Object>();


        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        GenericType<String> localVarReturnType = new GenericType<String>() {};
        try {
            String out =  apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, localVarPostBody, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
            System.out.println(out);
        } catch (ApiException e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    public void testHealthChecksApi() throws NutanixApiException {
        ApiClient apiClient = getApiClient();
        AuthconfigApi authconfigApi = new AuthconfigApi(apiClient);
        try {
            GetAuthDtoConfigAuthConfigDTO dto = authconfigApi.getAuthConfig();

            System.out.println();
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(),e);
        }
    }


    }
