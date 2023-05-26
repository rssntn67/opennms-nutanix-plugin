
package org.opennms.nutanix.connections;


import java.util.Optional;

public interface Connection {

    /**
     * Returns the alias of the connection.
     * The alias is a unique identifier representing a connection configuration.
     *
     * @return the alias
     */
    String getAlias();

    /**
     * Returns the URL of the orchestrator.
     * @return the orchestrator URL
     */
    String getPrismUrl();

    /**
     * Changes the URL of the orchestrator.
     * @param url the new URL
     */
    void setPrismUrl(final String url);

    /**
     * Returns the API key used to authenticate the connection.
     * @return the API key
     */
    String getApiKey();

    /**
     * Changes the API key used to authenticate the connection.
     * @param apiKey the new API key
     */
    void setApiKey(final String apiKey);

    /**
     * Save the altered connection config in the underlying store.
     */
    void save();

    /**
     * Test the connection.
     * @return The error, if any.
     */
    Optional<ConnectionValidationError> validate();

    void delete();

}
