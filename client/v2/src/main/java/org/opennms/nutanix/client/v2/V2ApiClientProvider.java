package org.opennms.nutanix.client.v2;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.opennms.nutanix.client.api.ApiClient;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.V2ClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.client.v2.api.AuthconfigApi;
import org.opennms.nutanix.client.v2.handler.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V2ApiClientProvider implements V2ClientProvider {

    private static final Logger LOG = LoggerFactory.getLogger(V2ApiClientProvider.class);

    public V2ApiClientProvider() {
    }

    private ApiClientExtention getClient(ApiClientCredentials credentials) {
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
    public ApiClient client(ApiClientCredentials credentials) {
        return new V2ApiClient(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_2).build();
    }

    @Override
    public boolean validate(ApiClientCredentials credentials) {
        AuthconfigApi authconfigApi = new AuthconfigApi(getClient(credentials));
        try {
            authconfigApi.getClientAuth();
        } catch (ApiException e) {
            LOG.info("validate: cannot connect to {}, -> {}", credentials,e.getMessage());
            return false;
        }
        return true;
    }
}
