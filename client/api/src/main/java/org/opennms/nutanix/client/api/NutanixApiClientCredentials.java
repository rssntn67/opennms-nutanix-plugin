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
    public final String prismUrl;

    /**
     * The username used to authenticate the connection to the PRISM ELEMENT.
     */
    public String username;

    /**
     * The password used to authenticate the connection to the PRISM ELEMENT.
     */
    public String password;

    private NutanixApiClientCredentials(final Builder builder) {
        this.prismUrl = Objects.requireNonNull(builder.prismUrl);
        this.username = builder.username;
        this.password = builder.password;
    }

    public static class Builder {
        private String prismUrl;
        private String username;
        private String password;


        private Builder() {
        }

        private Builder(final NutanixApiClientCredentials credentials) {
            this.prismUrl = credentials.prismUrl;
            this.username = credentials.username;
            this.password = credentials.password;
        }

        public Builder withPrismUrl(final String orchestratorUrl) {
            this.prismUrl = orchestratorUrl;
            return this;
        }

        public Builder withUsername(final String username) {
            this.username = username;
            return this;
        }


        public Builder withPassword(final String password) {
            this.password = password;
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
                .add("orchestratorUrl", this.prismUrl)
                .add("username", this.username)
                .add("password", this.password)
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
        return Objects.equals(this.prismUrl, that.prismUrl) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.prismUrl,
                 this.username, this.password);
    }

}
