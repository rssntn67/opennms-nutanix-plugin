package org.opennms.nutanix.client.api;

import org.opennms.nutanix.client.api.model.ApiVersion;

public interface ApiClientProvider {
    /**
     * Create a client for a Nutanix partner account.
     *
     * @param credentials the credentials to use for the client.
     * @return a NutanixApiClient client
     */
    ApiClientService client(final ApiClientCredentials credentials);

    ApiVersion getApiVersion();

    boolean validate(final ApiClientCredentials credentials);

}
