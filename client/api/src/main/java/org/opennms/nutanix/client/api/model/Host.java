package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class Host {
    public final String name;
    public final String uuid;

    private Host(Builder builder) {
        this.name= Objects.requireNonNull(builder.name);
        this.uuid=Objects.requireNonNull(builder.uuid);
    }
    public static class Builder {
        private String name;
        private String uuid;

        private Builder() {
        }

        public Host.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Host.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Host build() {
            return new Host(this);
        }

    }

    public static Builder builder() {
        return new Host.Builder();
    }

}
