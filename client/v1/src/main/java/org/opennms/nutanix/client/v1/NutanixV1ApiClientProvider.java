package org.opennms.nutanix.client.v1;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.ApiVersion;

public class NutanixV1ApiClientProvider implements NutanixApiClientProvider {
    private final boolean ignoreSslCertificateValidation;
    private final int length;

    public NutanixV1ApiClientProvider(boolean ignoreSslCertificateValidation, int length) {
        this.ignoreSslCertificateValidation=ignoreSslCertificateValidation;
        this.length=length;
    }

    private ApiClientExtention getClient(NutanixApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl+"/PrismGateway/services/rest/v1");
        String auth = credentials.username + ":" + credentials.password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setIgnoreSslCertificateValidation(ignoreSslCertificateValidation);
        apiClient.setLength(length);
        return apiClient;
    }

    @Override
    public NutanixApiClient client(NutanixApiClientCredentials credentials) {
        return new NutanixV1ApiClient(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_1).build();
    }

    @Override
    public boolean validate(NutanixApiClientCredentials credentials) {
        return false;
    }
}
