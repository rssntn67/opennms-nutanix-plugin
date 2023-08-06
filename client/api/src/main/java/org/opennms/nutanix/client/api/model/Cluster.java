package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class Cluster {

    public final String name;
    public final String uuid;
    public final String operationMode;
    public final Boolean isAvailable;


    private Cluster(Builder builder) {
        this.name= Objects.requireNonNull(builder.name);
        this.uuid=Objects.requireNonNull(builder.uuid);
        this.operationMode=Objects.requireNonNull(builder.operationMode);
        this.isAvailable=Objects.requireNonNull(builder.isAvailable);

    }
    public static class Builder {
        private String name;
        private String uuid;
        private String operationMode;

        private Boolean isAvailable;
        private Builder() {
        }

        public Cluster.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Cluster.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Cluster.Builder withOperationMode(final String operationMode) {
            this.operationMode = operationMode;
            return this;
        }

        public Cluster.Builder withIsAvailable(final boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }


        public Cluster build() {
            return new Cluster(this);
        }

    }

    public static Builder builder() {
        return new Cluster.Builder();
    }

}
