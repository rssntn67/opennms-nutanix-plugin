package org.opennms.nutanix.client.v3;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;
import org.opennms.nutanix.client.v3.api.VersionsApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.Versions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixV3ApiClientProvider implements NutanixApiClientProvider {
    private final int length;

    private static final Logger LOG = LoggerFactory.getLogger(NutanixV3ApiClientProvider.class);

    public NutanixV3ApiClientProvider(int length) {
        this.length=length;
    }

    private ApiClientExtention getClient(NutanixApiClientCredentials credentials) {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl+"/api/nutanix/v3");
        apiClient.setUsername(credentials.username);
        apiClient.setPassword(credentials.password);
        apiClient.setIgnoreSslCertificateValidation(credentials.ignoreSslCertificateValidation);
        apiClient.setLength(length);
        return apiClient;
    }
    @Override
    public NutanixApiClient client(NutanixApiClientCredentials credentials) {
        return new NutanixV3ApiClient(getClient(credentials));
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_3).build();
    }

    @Override
    public boolean validate(NutanixApiClientCredentials credentials) {
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
