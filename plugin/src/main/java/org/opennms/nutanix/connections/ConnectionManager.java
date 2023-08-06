package org.opennms.nutanix.connections;

import java.util.Collections;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.runtime.Container;
import org.opennms.integration.api.v1.runtime.RuntimeInfo;
import org.opennms.integration.api.v1.scv.Credentials;
import org.opennms.integration.api.v1.scv.SecureCredentialsVault;
import org.opennms.integration.api.v1.scv.immutables.ImmutableCredentials;
import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.clients.ClientManager;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

public class ConnectionManager {

    private static final String PREFIX = "nutanix_connection_";

    private final RuntimeInfo runtimeInfo;

    private final SecureCredentialsVault vault;

    private final ClientManager clientManager;

    public ConnectionManager(final RuntimeInfo runtimeInfo,
                             final SecureCredentialsVault vault,
                             final ClientManager clientManager) {
        this.runtimeInfo = Objects.requireNonNull(runtimeInfo);
        this.vault = Objects.requireNonNull(vault);
        this.clientManager = Objects.requireNonNull(clientManager);
    }

    /**
     * Returns a set of all available connection aliases.
     *
     * @return the set of aliases
     */
    public Set<String> getAliases() {
        this.ensureCore();

        return this.vault.getAliases().stream()
                .filter(alias -> alias.startsWith(PREFIX))
                .map(alias -> alias.substring(PREFIX.length()))
                .collect(Collectors.toSet());
    }

    /**
     * Returns a connection config for the given alias.
     *
     * @param alias the alias of the connection config to retrieve
     * @return The connection config or {@code Optional#empty()} of no such alias exists
     */
    public Optional<Connection> getConnection(final String alias) {
        this.ensureCore();

        final var credentials = this.vault.getCredentials(PREFIX + alias.toLowerCase());
        if (credentials == null) {
            return Optional.empty();
        }

        return Optional.of(new ConnectionImpl(alias, ConnectionManager.fromStore(credentials)));
    }

    /**
     * Creates a connection under the given alias.
     *
     * @param alias           the alias of the connection to add
     * @param prismUrl        the URL of the prism server
     * @param apiKey          the API key used to authenticate the connection
     */
    public Connection newConnection(final String alias, final String prismUrl, final String apiKey) {
        this.ensureCore();

        return new ConnectionImpl(alias, NutanixApiClientCredentials.builder()
                                                                      .withPrismUrl(prismUrl)
                                                                      .withApiKey(apiKey)
                                                                      .build());
    }

    /**
     * Deletes a connection under the given alias.
     *
     * @param alias the alias of the connection to delete
     * @return <b>true</b> if an existing connection with given alias was found and deleted and <b>false</b> if no
     * connection with given alias was not found
     */
    public boolean deleteConnection(final String alias) {
        this.ensureCore();

        final var connection = this.getConnection(alias);
        if (connection.isEmpty()) {
            return false;
        }
        connection.get().delete();
        return true;
    }

    public Optional<NutanixApiClient> getClient(final String alias) throws NutanixApiException {
        this.ensureCore();

        final var connection = this.getConnection(alias);
        if (connection.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.clientManager.getClient(asNutanixCredentials(connection.get())));
    }


    private static NutanixApiClientCredentials asNutanixCredentials(Connection connection) {
        return NutanixApiClientCredentials.builder()
                                            .withApiKey(connection.getApiKey())
                                            .withPrismUrl(connection.getPrismUrl())
                                            .build();
    }

    private static NutanixApiClientCredentials fromStore(final Credentials credentials) {
        if (Strings.isNullOrEmpty(credentials.getUsername())) {
            throw new IllegalStateException("Orchestrator URL (username) is missing");
        }
        final var orchestratorUrl = credentials.getUsername();

        if (Strings.isNullOrEmpty(credentials.getPassword())) {
            throw new IllegalStateException("API key (password) is missing");
        }
        final var apiKey = credentials.getPassword();

        return NutanixApiClientCredentials.builder()
                                            .withPrismUrl(orchestratorUrl)
                                            .withApiKey(apiKey)
                                            .build();
    }

    private class ConnectionImpl implements Connection {
        private final String alias;

        private NutanixApiClientCredentials credentials;

        private ConnectionImpl(final String alias, final NutanixApiClientCredentials credentials) {
            this.alias = Objects.requireNonNull(alias).toLowerCase();
            this.credentials = Objects.requireNonNull(credentials);
        }

        @Override
        public String getAlias() {
            return this.alias;
        }

        @Override
        public String getPrismUrl() {
            return this.credentials.prismUrl;
        }

        @Override
        public void setPrismUrl(final String url) {
            this.credentials = NutanixApiClientCredentials.builder(this.credentials)
                                                            .withPrismUrl(url)
                                                            .build();
        }

        @Override
        public String getApiKey() {
            return this.credentials.apiKey;
        }

        @Override
        public void setApiKey(final String apiKey) {
            this.credentials = NutanixApiClientCredentials.builder(this.credentials)
                                                            .withApiKey(apiKey)
                                                            .build();
        }

        @Override
        public void save() {
            // Purge cached client with old credentials
            ConnectionManager.this.vault.setCredentials(PREFIX + this.alias, this.asCredentials());
        }

        @Override
        public Optional<ConnectionValidationError> validate() {
            return ConnectionManager.this.clientManager.validate(ConnectionManager.asNutanixCredentials(this));
        }

        @Override
        public void delete() {
            ConnectionManager.this.vault.deleteCredentials(PREFIX + this.alias);
        }

        private Credentials asCredentials() {
            return new ImmutableCredentials(this.credentials.prismUrl, this.credentials.apiKey, Collections.emptyMap());
        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                              .add("alias", this.alias)
                              .add("orchestratorUrl", this.credentials.prismUrl)
                              .add("apiKey", "******")
                              .toString();
        }
    }

    private void ensureCore() {
        if (this.runtimeInfo.getContainer() != Container.OPENNMS) {
            throw new IllegalStateException("Operation only allowed on OpenNMS instance");
        }
    }
}
