package org.opennms.nutanix.client.v2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.HashSet;
import java.util.Set;

import javax.ws.rs.core.HttpHeaders;

import org.junit.Assert;
import org.junit.Test;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v2.api.VmsApi;
import org.opennms.nutanix.client.v2.handler.ApiException;
import org.opennms.nutanix.client.v2.model.GetBaseEntityCollectionltgetDtoUhuraVmConfigDTOgt;

public class NutanixApiClientV2Test {

    private ApiClientExtention getApiClient() {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath("https://nutanix.arsinfo.it:9440/PrismGateway/services/rest/v2.0");
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
    public void testVmsApi() throws NutanixApiException {

        ApiClientExtention apiClientExtention = getApiClient();
        VmsApi vmsApi = new VmsApi(apiClientExtention);
        int count = 0;
        int total;
        int endIndex;
        int startIndex = 0;
        final Set<String> uuid = new HashSet<>();
        do {
            GetBaseEntityCollectionltgetDtoUhuraVmConfigDTOgt dto;
            try {
                dto = vmsApi.getVMs(null, startIndex,apiClientExtention.getLength() , null,null,true, true);
                        } catch (ApiException e) {
                throw new NutanixApiException(e.getMessage(), e);
            }
            endIndex=dto.getMetadata().getEndIndex();
            Assert.assertEquals(startIndex, dto.getMetadata().getStartIndex().intValue());
            Assert.assertEquals(endIndex - startIndex, dto.getMetadata().getCount().intValue());

            startIndex=endIndex;
            total = dto.getMetadata().getGrandTotalEntities();
            System.out.println(dto.getMetadata());
            count+=dto.getMetadata().getCount();
            dto.getEntities().forEach(vm -> uuid.add(vm.getUuid()));
        } while (endIndex < total );

        Assert.assertEquals(count, total);
        Assert.assertEquals(count, uuid.size());
        System.out.println(uuid);

    }

}
