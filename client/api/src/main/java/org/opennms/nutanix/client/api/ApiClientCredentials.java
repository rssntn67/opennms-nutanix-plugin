package org.opennms.nutanix.client.api;

import java.util.Objects;

import com.google.common.base.MoreObjects;

/**
 * Credentials for a Nutanix API connection.
 */
public class ApiClientCredentials {
    /**
     * The URL of the Nutanix orchestrator.
     */
    public final String prismUrl;

    /**
     * The username used to authenticate the connection to the PRISM ELEMENT.
     */
    public final String username;

    /**
     * The password used to authenticate the connection to the PRISM ELEMENT.
     */
    public final String password;

    /**
     * Wheter to check SSL Certificate
     */
    public final Boolean ignoreSslCertificateValidation;

    public final Integer length;

    private ApiClientCredentials(final Builder builder) {
        this.prismUrl = Objects.requireNonNull(builder.prismUrl);
        this.username = builder.username;
        this.password = builder.password;
        this.ignoreSslCertificateValidation = builder.ignoreSslCertificateValidation;
        this.length = builder.length;
    }

    public static class Builder {
        private String prismUrl;
        private String username;
        private String password;

        private boolean ignoreSslCertificateValidation = false;

        private int length = 20;

        private Builder() {
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

        public Builder withIgnoreSslCertificateValidation(final Boolean ignoreSslCertificateValidation) {
            this.ignoreSslCertificateValidation = ignoreSslCertificateValidation;
            return this;
        }

        public Builder withLength(final int length){
            this.length=length;
            return this;
        }

        public ApiClientCredentials build() {
            return new ApiClientCredentials(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public static Builder builder(ApiClientCredentials credentials) {
        return builder()
                .withPrismUrl(credentials.prismUrl)
                .withUsername(credentials.username)
                .withPassword(credentials.password)
                .withIgnoreSslCertificateValidation(credentials.ignoreSslCertificateValidation)
                .withLength(credentials.length);

    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("prismUrl", this.prismUrl)
                .add("username", this.username)
                .add("password", "*****")
                .add("ignoreSslCertificateValidation", this.ignoreSslCertificateValidation)
                .add("length", this.length)
                .toString();
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ApiClientCredentials)) {
            return false;
        }
        final ApiClientCredentials that = (ApiClientCredentials) o;
        return Objects.equals(this.prismUrl, that.prismUrl) &&
                Objects.equals(this.username, that.username) &&
                Objects.equals(this.password, that.password) &&
                Objects.equals(this.ignoreSslCertificateValidation, that.ignoreSslCertificateValidation);

    }

    @Override
    public int hashCode() {
        return Objects.hash(this.prismUrl,
                 this.username, this.password, this.ignoreSslCertificateValidation);
    }

}
