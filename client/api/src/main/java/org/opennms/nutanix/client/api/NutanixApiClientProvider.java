package org.opennms.nutanix.client.api;

import org.opennms.nutanix.client.api.model.ApiVersion;

public interface NutanixApiClientProvider {
    /**
     * Create a client for a Nutanix partner account.
     *
     * @param credentials the credentials to use for the client.
     * @return a NutanixApiClient client
     * @throws NutanixApiException
     */
    NutanixApiClient client(final NutanixApiClientCredentials credentials) throws NutanixApiException;

    ApiVersion getApiVersion();

    boolean validate(final NutanixApiClientCredentials credentials) throws NutanixApiException;

}
