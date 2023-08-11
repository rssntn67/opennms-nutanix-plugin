package org.opennms.nutanix.client.v3;

import org.opennms.nutanix.client.api.ApiClient;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.V3ClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.client.v3.api.VersionsApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.Versions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class V3ApiClientProvider implements V3ClientProvider {
    private static final Logger LOG = LoggerFactory.getLogger(V3ApiClientProvider.class);

    public V3ApiClientProvider() {
    }

    private ApiClientExtention getClient(ApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl+"/api/nutanix/v3");
        apiClient.setUsername(credentials.username);
        apiClient.setPassword(credentials.password);
        apiClient.setIgnoreSslCertificateValidation(credentials.ignoreSslCertificateValidation);
        apiClient.setLength(credentials.length);
        return apiClient;
    }
    @Override
    public ApiClient client(ApiClientCredentials credentials) {
        return new V3ApiClient(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_3).build();
    }

    @Override
    public boolean validate(ApiClientCredentials credentials) {
        VersionsApi versionsApi = new VersionsApi(getClient(credentials));
        Versions versions;
        try {
            versions= versionsApi.versionsGet();
        } catch (ApiException e) {
            LOG.info("validate: cannot connect to {}, -> {}", credentials,e.getMessage());
            return false;
        }
        return versions != null && "3.1".equalsIgnoreCase(versions.getMajorVersion() + "." + versions.getMinorVersion());
    }
}
