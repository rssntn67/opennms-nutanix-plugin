package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class Hypervisor {
    public final String ip;
    public final String version;
    public final String type;


    private Hypervisor(Builder builder) {
        this.ip = Objects.requireNonNull(builder.ip);
        this.version = Objects.requireNonNull(builder.version);
        this.type = Objects.requireNonNull(builder.type);
    }

    public static class Builder {

        private String ip;
        private String version;
        private String type;

        public Builder setIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder setVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder setType(String type) {
            this.type = type;
            return this;
        }

        public Hypervisor build() {
            return new Hypervisor(this);
        }
    }
    public static Hypervisor.Builder builder() {
        return new Hypervisor.Builder();
    }
}
