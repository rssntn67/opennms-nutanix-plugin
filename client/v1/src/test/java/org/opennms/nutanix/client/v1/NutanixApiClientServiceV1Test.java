package org.opennms.nutanix.client.v1;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.ws.rs.core.GenericType;
import javax.ws.rs.core.HttpHeaders;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v1.api.AuthconfigApi;
import org.opennms.nutanix.client.v1.api.VmsApi;
import org.opennms.nutanix.client.v1.handler.ApiClient;
import org.opennms.nutanix.client.v1.handler.ApiException;
import org.opennms.nutanix.client.v1.handler.Pair;
import org.opennms.nutanix.client.v1.model.GetAuthDtoConfigAuthConfigDTO;
import org.opennms.nutanix.client.v1.model.VMEntity;
import org.opennms.nutanix.client.v1.model.VMCollectionEntity;

public class NutanixApiClientServiceV1Test {


    private ApiClientExtention getApiClient() {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/PrismGateway/services/rest/v1");
        String auth = System.getenv("NTX_USER") + ":" + System.getenv("NTX_PASS");
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setDebugging(true);
        apiClient.setIgnoreSslCertificateValidation(true);
        apiClient.setLength(20);
        return apiClient;
    }

    @Test
    public void testEnvVariable() {
        Assert.assertNotNull(System.getenv("NTX_USER"));
        Assert.assertNotNull(System.getenv("NTX_PASS"));
    }

    @Test
    public void testVmsGet() throws NutanixApiException {
        ApiClient apiClient = getApiClient();

        // create path and map variables
        String localVarPath = "/vms/";

        // query params
        List<Pair> localVarQueryParams = new ArrayList<>();
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "page", 1));
        localVarQueryParams.addAll(apiClient.parameterToPairs("", "count", 1));

        Map<String, String> localVarHeaderParams = new HashMap<>();
        Map<String, Object> localVarFormParams = new HashMap<>();


        final String[] localVarAccepts = {
                "application/json"
        };
        final String localVarAccept = apiClient.selectHeaderAccept(localVarAccepts);

        final String[] localVarContentTypes = {

        };
        final String localVarContentType = apiClient.selectHeaderContentType(localVarContentTypes);

        String[] localVarAuthNames = new String[] {  };

        GenericType<VMCollectionEntity> localVarReturnType = new GenericType<>() {};
        try {
            VMCollectionEntity vms = apiClient.invokeAPI(localVarPath, "GET", localVarQueryParams, null, localVarHeaderParams, localVarFormParams, localVarAccept, localVarContentType, localVarAuthNames, localVarReturnType);
            System.out.println(vms.getMetadata());
            System.out.println(vms.getAdditionalProperties());
            vms.getEntities().forEach(vm -> System.out.println(vm.getVmName()));
            vms.getEntities().forEach(vm -> System.out.println(vm.getVmType()));
            vms.getEntities().forEach(vm -> System.out.println(vm.getUuid()));
            vms.getEntities().forEach(vm -> System.out.println(vm.getPowerState()));
            vms.getEntities().forEach(vm -> System.out.println(vm.getStats()));
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(),e);
        }

    }

    @Test
    public void testVmsApi() throws NutanixApiException {
        ApiClientExtention apiClient = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClient);
        int count = 0;
        int total;
        int page = 1;
        int endIndex;
        final Set<String> uuid = new HashSet<>();
        do {
            int startIndex;
            VMCollectionEntity dto;
            try {
                dto = vmsApi.getVMs(page,apiClient.getLength());
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
            startIndex=dto.getMetadata().getStartIndex();
            endIndex=dto.getMetadata().getEndIndex();
            Assert.assertEquals(endIndex - startIndex + 1, dto.getEntities().size());
            total = dto.getMetadata().getGrandTotalEntities();
            System.out.println(dto.getMetadata());
            count+=dto.getEntities().size();
            page++;
            dto.getEntities().forEach(vm -> uuid.add(vm.getUuid()));
            dto.getEntities().forEach(System.out::println);
            dto.getEntities().forEach(vm -> Assert.assertNotNull(vm.getPowerState()));
        } while (endIndex < total );

        Assert.assertEquals(count, total);
        Assert.assertEquals(count, uuid.size());
        System.out.println(uuid);
        System.out.println("total vms: " + total);

    }

    @Test
    public void testVmsApiGetVm() throws NutanixApiException {
        ApiClientExtention apiClient = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClient);
        String uuid = "d04f11c9-690b-4fa9-8d17-0c0de2e92c77";

            try {
                VMEntity vmEntity = vmsApi.getVM(uuid);
                Assert.assertNotNull(vmEntity);
                Assert.assertEquals(uuid,vmEntity.getUuid());
                Assert.assertEquals("SVL-TOMCATS2-AS2",vmEntity.getVmName());
                Assert.assertNotNull(vmEntity.getStats());
                System.out.println(vmEntity.getStats());
            } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }

    }

    @Test
    public void testHealthChecksApi() throws NutanixApiException {
        ApiClient apiClient = getApiClient();
        AuthconfigApi authconfigApi = new AuthconfigApi(apiClient);
        try {
            GetAuthDtoConfigAuthConfigDTO dto = authconfigApi.getAuthConfig();

            System.out.println(dto);
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(),e);
        }
    }


    }
