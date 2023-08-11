package org.opennms.nutanix.client.v1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.opennms.nutanix.client.api.ApiClient;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.V1ClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;

public class V1ApiClientProvider implements V1ClientProvider {
    public V1ApiClientProvider() {
    }

    private ApiClientExtention getClient(ApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl+"/PrismGateway/services/rest/v1");
        String auth = credentials.username + ":" + credentials.password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setIgnoreSslCertificateValidation(credentials.ignoreSslCertificateValidation);
        apiClient.setLength(credentials.length);
        return apiClient;
    }

    @Override
    public ApiClient client(ApiClientCredentials credentials) {
        return new V1ApiClient(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_1).build();
    }

    @Override
    public boolean validate(ApiClientCredentials credentials) {
        return false;
    }
}
