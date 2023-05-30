package org.opennms.nutanix.client.v3;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.v3.api.VersionsApi;
import org.opennms.nutanix.client.v3.handler.ApiException;
import org.opennms.nutanix.client.v3.model.Versions;

public class NutanixV3ApiClientProvider implements NutanixApiClientProvider {
    private final boolean ignoreSslCertificateValidation;
    private final int length;

    public NutanixV3ApiClientProvider(boolean ignoreSslCertificateValidation, int length) {
        this.ignoreSslCertificateValidation=ignoreSslCertificateValidation;
        this.length=length;
    }

    @Override
    public NutanixApiClient client(NutanixApiClientCredentials credentials) throws NutanixApiException {
        ApiClientExtention apiClient = new ApiClientExtention();
        apiClient.setBasePath(credentials.prismUrl);
        apiClient.setApiKey(credentials.apiKey);
        apiClient.setIgnoreSslCertificateValidation(ignoreSslCertificateValidation);
        apiClient.setLength(length);
        VersionsApi versionsApi = new VersionsApi(apiClient);
        Versions versions;
        try {
            versions= versionsApi.versionsGet();
        } catch (ApiException e) {
            throw new NutanixApiException(e.getMessage(), e);
        }
        if (versions != null && "3.1".equalsIgnoreCase(versions.getMajorVersion()+"."+versions.getMinorVersion()))
            return new NutanixV3ApiClient(apiClient);
        throw new NutanixApiException("Unsupported Api version: " + versions.getMajorVersion()+"."+versions.getMinorVersion() + " is not 3.1");
    }
}
