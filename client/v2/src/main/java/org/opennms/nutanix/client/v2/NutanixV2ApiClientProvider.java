package org.opennms.nutanix.client.v2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;

public class NutanixV2ApiClientProvider implements NutanixApiClientProvider {
    private final boolean ignoreSslCertificateValidation;

    private int length = 20;

    public NutanixV2ApiClientProvider(boolean ignoreSslCertificateValidation, int lenght) {
        this.ignoreSslCertificateValidation=ignoreSslCertificateValidation;
        this.length = lenght;
    }

    private ApiClientExtention getClient(NutanixApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl);
        String auth = credentials.username + ":" + credentials.password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setIgnoreSslCertificateValidation(ignoreSslCertificateValidation);
        apiClient.setLength(20);
        return apiClient;
    }

    @Override
    public NutanixApiClient client(NutanixApiClientCredentials credentials) {
        return new NutanixV2ApiClient(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_2).build();
    }

    @Override
    public boolean validate(NutanixApiClientCredentials credentials) {
        return false;
    }
}
