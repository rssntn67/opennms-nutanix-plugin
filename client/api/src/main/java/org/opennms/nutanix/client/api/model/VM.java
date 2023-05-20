package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class VM {
    public final String name;
    public final String uuid;

    private VM(Builder builder) {
        this.name=Objects.requireNonNull(builder.name);
        this.uuid=Objects.requireNonNull(builder.uuid);
    }
    public static class Builder {
        private String name;
        private String uuid;

        private Builder() {
        }

        public VM.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public VM.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public VM build() {
            return new VM(this);
        }

    }

    public static Builder builder() {
        return new Builder();
    }

}
