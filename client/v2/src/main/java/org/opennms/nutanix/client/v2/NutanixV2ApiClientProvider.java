package org.opennms.nutanix.client.v2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.client.v2.api.AuthconfigApi;
import org.opennms.nutanix.client.v2.handler.ApiException;
import org.opennms.nutanix.client.v2.model.GetAuthDtoConfigAuthConfigDTO;
import org.opennms.nutanix.client.v2.model.GetAuthDtoConfigClientAuthDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixV2ApiClientProvider implements NutanixApiClientProvider {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixV2ApiClientProvider.class);

    public NutanixV2ApiClientProvider() {
    }

    private ApiClientExtention getClient(NutanixApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl+"/PrismGateway/services/rest/v2.0");
        String auth = credentials.username + ":" + credentials.password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setIgnoreSslCertificateValidation(credentials.ignoreSslCertificateValidation);
        apiClient.setLength(credentials.length);
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
        AuthconfigApi authconfigApi = new AuthconfigApi(getClient(credentials));
        try {
            GetAuthDtoConfigClientAuthDTO dto = authconfigApi.getClientAuth();
        } catch (ApiException e) {
            LOG.info("validate: cannot connect to {}, -> {}", credentials,e.getMessage());
            return false;
        }
        return true;
    }
}
