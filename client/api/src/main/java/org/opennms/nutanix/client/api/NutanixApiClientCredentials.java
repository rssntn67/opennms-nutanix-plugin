package org.opennms.nutanix.client.api;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Credentials for a Nutanix API connection.
 */
public class NutanixApiClientCredentials {
    /**
     * The URL of the Nutanix orchestrator.
     */
    public final String orchestratorUrl;

    /**
     * The API key used to authenticate the connection to the orchestrator.
     */
    public final String apiKey;

    public NutanixApiClientCredentials(final String orchestratorUrl,
                                         final String apiKey) {
        this.orchestratorUrl = Objects.requireNonNull(orchestratorUrl);
        this.apiKey = Objects.requireNonNull(apiKey);
    }

    private NutanixApiClientCredentials(final Builder builder) {
        this.orchestratorUrl = Objects.requireNonNull(builder.orchestratorUrl);
        this.apiKey = Objects.requireNonNull(builder.apiKey);
    }

    public static class Builder {
        private String orchestratorUrl;
        private String apiKey;

        private Builder() {
        }

        private Builder(final NutanixApiClientCredentials credentials) {
            this.orchestratorUrl = credentials.orchestratorUrl;
            this.apiKey = credentials.apiKey;
        }

        public Builder withOrchestratorUrl(final String orchestratorUrl) {
            this.orchestratorUrl = orchestratorUrl;
            return this;
        }

        public Builder withApiKey(final String apiKey) {
            this.apiKey = apiKey;
            return this;
        }

        public NutanixApiClientCredentials build() {
            return new NutanixApiClientCredentials(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(final NutanixApiClientCredentials credentials) {
        return new Builder(credentials);
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("orchestratorUrl", this.orchestratorUrl)
                .add("apiKey", this.apiKey)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof NutanixApiClientCredentials)) {
            return false;
        }
        final NutanixApiClientCredentials that = (NutanixApiClientCredentials) o;
        return Objects.equals(this.orchestratorUrl, that.orchestratorUrl) &&
                Objects.equals(this.apiKey, that.apiKey);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.orchestratorUrl,
                this.apiKey);
    }

}
