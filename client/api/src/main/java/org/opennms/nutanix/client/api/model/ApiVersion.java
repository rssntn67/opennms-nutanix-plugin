package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class ApiVersion {
    public enum Version {
        VERSION_0_8,
        VERSION_1,
        VERSION_2,
        VERSION_3
    }

    public final Version version;

    private ApiVersion(ApiVersion.Builder builder) {
        this.version= Objects.requireNonNull(builder.version);
    }


    public static class Builder {

        private Version version;
        private Builder() {

        }

        public ApiVersion.Builder withVersion(final Version version) {
            this.version = version;
            return this;
        }

        public ApiVersion build() {
            return new ApiVersion(this);
        }

    }

    public static ApiVersion.Builder builder() {
        return new ApiVersion.Builder();
    }
}