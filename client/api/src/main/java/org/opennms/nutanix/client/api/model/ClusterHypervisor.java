package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class ClusterHypervisor {
    public final String ip;
    public final String version;
    public final String type;


    private ClusterHypervisor(Builder builder) {
        this.ip = Objects.requireNonNull(builder.ip);
        this.version = Objects.requireNonNull(builder.version);
        this.type = Objects.requireNonNull(builder.type);
    }

    public static class Builder {

        private String ip;
        private String version;
        private String type;

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public ClusterHypervisor build() {
            return new ClusterHypervisor(this);
        }
    }
    public static ClusterHypervisor.Builder builder() {
        return new ClusterHypervisor.Builder();
    }
}
