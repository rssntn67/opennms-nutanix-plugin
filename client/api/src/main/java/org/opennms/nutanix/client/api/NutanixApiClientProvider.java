package org.opennms.nutanix.client.api;

public interface NutanixApiClientProvider {
    /**
     * Create a client for a Nutanix partner account.
     *
     * @param credentials the credentials to use for the client.
     * @return a partner client
     * @throws NutanixApiException
     */
    NutanixApiClient client(final NutanixApiClientCredentials credentials) throws NutanixApiException;
}
