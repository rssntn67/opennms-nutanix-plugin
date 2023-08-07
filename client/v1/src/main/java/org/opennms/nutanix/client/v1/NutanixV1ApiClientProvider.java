package org.opennms.nutanix.client.v1;

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

    @Override
    public NutanixApiClient client(NutanixApiClientCredentials credentials) {
        return null;
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
