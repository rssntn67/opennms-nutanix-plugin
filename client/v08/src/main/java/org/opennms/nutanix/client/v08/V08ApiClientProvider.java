package org.opennms.nutanix.client.v08;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

import javax.ws.rs.core.HttpHeaders;

import org.opennms.nutanix.client.api.ApiClientService;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.V08ClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.client.v08.api.HaApi;
import org.opennms.nutanix.client.v08.handler.ApiException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V08ApiClientProvider implements V08ClientProvider {
    private static final Logger LOG = LoggerFactory.getLogger(V08ApiClientProvider.class);

    public V08ApiClientProvider() {
    }

    private ApiClientExtention getClient(ApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl+"/api/nutanix/v0.8");
        String auth = credentials.username + ":" + credentials.password;
        byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(StandardCharsets.UTF_8));
        String authHeader = "Basic " + new String(encodedAuth);
        apiClient.addDefaultHeader(HttpHeaders.AUTHORIZATION, authHeader);
        apiClient.setIgnoreSslCertificateValidation(credentials.ignoreSslCertificateValidation);
        apiClient.setLength(credentials.length);
        return apiClient;
    }

    @Override
    public ApiClientService client(ApiClientCredentials credentials)  {
        return new V08ApiClientService(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_0_8).build();
    }

    @Override
    public boolean validate(ApiClientCredentials credentials) {
        HaApi haApi = new HaApi(getClient(credentials));
        try {
            haApi.getHaConfig();
        } catch (ApiException e) {
            LOG.info("validate: cannot connect to {}, -> {}", credentials, e.getMessage());
            return false;
        }
        return true;
    }
}
