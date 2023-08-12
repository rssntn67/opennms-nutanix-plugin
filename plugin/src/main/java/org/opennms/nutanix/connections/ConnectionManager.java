package org.opennms.nutanix.connections;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.opennms.integration.api.v1.runtime.Container;
import org.opennms.integration.api.v1.runtime.RuntimeInfo;
import org.opennms.integration.api.v1.scv.Credentials;
import org.opennms.integration.api.v1.scv.SecureCredentialsVault;
import org.opennms.integration.api.v1.scv.immutables.ImmutableCredentials;
import org.opennms.nutanix.client.api.ApiClient;
import org.opennms.nutanix.client.api.ApiClientCredentials;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.clients.ClientManager;

import com.google.common.base.MoreObjects;
import com.google.common.base.Strings;

public class ConnectionManager {

    private static final String PREFIX = "nutanix_connection_";
    public static final String PRISM_URL_KEY = "prismUrl";
    public static final String IGNORE_SSL_CERTIFICATE_VALIDATION_KEY = "ignoreSslCertificateValidation";

    public static final String LENGTH_KEY="length";
    public static final String ALIAS_KEY = "alias";

    public static final String USERNAME_KEY = "username";
    public static final String PASSWORD_KEY = "password";




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
     * Creates a basic authentication connection under the given alias.
     *
     * @param alias           the alias of the connection to add
     * @param prismUrl        the URL of the prism server
     * @param username          the username to authenticate the connection
     * @param password          the password to authenticate the connection
     * @param ignoreSslCerticateValidation          ignore Ssl Certificate Validation
     * @param length         number of object retrieved by the api client
     */
    public Connection newConnection(final String alias, final String prismUrl, final String username, final String password, final boolean ignoreSslCerticateValidation, final int length) {
        this.ensureCore();

        return new ConnectionImpl(alias, ApiClientCredentials.builder()
                .withPrismUrl(prismUrl)
                .withUsername(username)
                .withPassword(password)
                .withIgnoreSslCertificateValidation(ignoreSslCerticateValidation)
                .withLength(length)
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

    public Optional<ApiClient> getClient(final String alias) throws NutanixApiException {
        this.ensureCore();

        final var connection = this.getConnection(alias);
        if (connection.isEmpty()) {
            return Optional.empty();
        }

        return Optional.of(this.clientManager.getClient(asNutanixCredentials(connection.get())));
    }


    private static ApiClientCredentials asNutanixCredentials(Connection connection) {
        return ApiClientCredentials.builder()
                .withUsername(connection.getUsername())
                .withPassword(connection.getPassword())
                .withPrismUrl(connection.getPrismUrl())
                .withIgnoreSslCertificateValidation(connection.isIgnoreSslCertificateValidation())
                .withLength(connection.getLength())
                .build();
    }

    private static ApiClientCredentials fromStore(final Credentials credentials) {


        if (Strings.isNullOrEmpty(credentials.getPassword())) {
            throw new IllegalStateException("API password is missing");
        }
        if (Strings.isNullOrEmpty(credentials.getUsername())) {
            throw new IllegalStateException("API username is missing");
        }

        if (Strings.isNullOrEmpty(credentials.getAttribute(PRISM_URL_KEY))) {
            throw new IllegalStateException("Prism URL is missing");
        }

        if (Strings.isNullOrEmpty(credentials.getAttribute(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY))) {
            throw new IllegalStateException("Ignore  SSL CERTIFICATION Validation is missing");
        }

        if (Strings.isNullOrEmpty(credentials.getAttribute(LENGTH_KEY))) {
            throw new IllegalStateException("Length key is missing");
        }

        final var prismUrl = credentials.getAttribute(PRISM_URL_KEY);

        final var username = credentials.getUsername();
        final var password = credentials.getPassword();
        final var ignoreSslCertificateValidation = Boolean.parseBoolean(credentials.getAttribute(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY));
        final var length = Integer.parseInt(credentials.getAttribute(LENGTH_KEY));

        return ApiClientCredentials.builder()
                .withPrismUrl(prismUrl)
                .withUsername(username)
                .withPassword(password)
                .withIgnoreSslCertificateValidation(ignoreSslCertificateValidation)
                .withLength(length)
                .build();
        }


    private class ConnectionImpl implements Connection {
        private final String alias;

        private ApiClientCredentials credentials;

        private ConnectionImpl(final String alias, final ApiClientCredentials credentials) {
            this.alias = Objects.requireNonNull(alias).toLowerCase();
            this.credentials = Objects.requireNonNull(credentials);
        }

        @Override
        public int getLength() {
            return this.credentials.length;
        }

        @Override
        public void setLength(int length) {
            this.credentials = ApiClientCredentials.builder(this.credentials).withLength(length).build();
        }

        @Override
        public boolean isIgnoreSslCertificateValidation() {
            return this.credentials.ignoreSslCertificateValidation;
        }

        @Override
        public void setIgnoreSslCertificateValidation(boolean ignoreSslCertificateValidation) {
            this.credentials = ApiClientCredentials.builder(this.credentials)
                    .withIgnoreSslCertificateValidation(ignoreSslCertificateValidation)
                    .build();
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
            this.credentials = ApiClientCredentials.builder()
                                                            .withPrismUrl(url)
                                                            .build();
        }
        @Override
        public String getUsername() {
            return this.credentials.username;
        }

        @Override
        public void setUsername(final String username) {
            this.credentials = ApiClientCredentials.builder(this.credentials)
                    .withUsername(username)
                    .build();
        }

        @Override
        public String getPassword() {
            return this.credentials.password;
        }

        @Override
        public void setPassword(final String password) {
            this.credentials = ApiClientCredentials.builder(this.credentials)
                    .withPassword(password)
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
            Map<String,String> credentialMap = new HashMap<>();
            credentialMap.put(PRISM_URL_KEY, this.credentials.prismUrl);
            credentialMap.put(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY, String.valueOf(this.credentials.ignoreSslCertificateValidation));
            credentialMap.put(LENGTH_KEY, this.credentials.length.toString());
            return new ImmutableCredentials(this.credentials.username, this.credentials.password, credentialMap);


        }

        @Override
        public String toString() {
            return MoreObjects.toStringHelper(this)
                              .add("alias", this.alias)
                              .add(PRISM_URL_KEY, this.credentials.prismUrl)
                              .add("username", this.credentials.username)
                              .add("password", "******")
                              .add(IGNORE_SSL_CERTIFICATE_VALIDATION_KEY, this.credentials.ignoreSslCertificateValidation)
                              .add(LENGTH_KEY, this.credentials.length)
                    .toString();
        }
    }

    private void ensureCore() {
        if (this.runtimeInfo.getContainer() != Container.OPENNMS) {
            throw new IllegalStateException("Operation only allowed on OpenNMS instance");
        }
    }
}
