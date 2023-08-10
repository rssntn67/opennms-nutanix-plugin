package org.opennms.nutanix.client.api.model;

public class ClusterSoftware {
    public final String status;
    public final String version;
    public final String softwareType;

    public ClusterSoftware(Builder builder ) {
        this.status = builder.status;
        this.version = builder.version;
        this.softwareType = builder.softwareType;
    }

    public static class Builder {

        private String status;
        private String version;
        private String softwareType;

        public Builder withStatus(String status) {
            this.status = status;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withSoftwareType(String softwareType) {
            this.softwareType = softwareType;
            return this;
        }

        public ClusterSoftware build() {
            return new ClusterSoftware(this);
        }
    }
    
    public static ClusterSoftware.Builder builder() {
        return new ClusterSoftware.Builder();
    }
}
