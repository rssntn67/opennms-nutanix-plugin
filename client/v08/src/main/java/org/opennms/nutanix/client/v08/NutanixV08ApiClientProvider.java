package org.opennms.nutanix.client.v08;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiClientProvider;
import org.opennms.nutanix.client.api.model.ApiVersion;

public class NutanixV08ApiClientProvider implements NutanixApiClientProvider {
    private final boolean ignoreSslCertificateValidation;
    private final int length;

    public NutanixV08ApiClientProvider(boolean ignoreSslCertificateValidation, int length) {
        this.ignoreSslCertificateValidation=ignoreSslCertificateValidation;
        this.length=length;
    }

    @Override
    public NutanixApiClient client(NutanixApiClientCredentials credentials)  {
        return null;
    }

    @Override
    public ApiVersion getApiVersion() {
        return ApiVersion.builder().withVersion(ApiVersion.Version.VERSION_0_8).build();
    }

    @Override
    public boolean validate(NutanixApiClientCredentials credentials) {
        return false;
    }
}
