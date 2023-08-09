package org.opennms.nutanix.client.api.model;

import java.util.List;
import java.util.Objects;

public class Cluster {

    //From Cluster.status
    public final String state;
    public final String name;

    public final List<Hypervisor> nodes;
    //From Cluster metadata
    public final String uuid;
    public final String operationMode;
    public final Boolean isAvailable;

    public final String domainAwarenessLevel;
    public final List<String> enabledFeatureList;

    private Cluster(Builder builder) {
        this.state = Objects.requireNonNull(builder.state);
        this.name = Objects.requireNonNull(builder.name);
        this.nodes = Objects.requireNonNull(builder.nodes);
        this.uuid = Objects.requireNonNull(builder.uuid);
        this.operationMode = Objects.requireNonNull(builder.operationMode);
        this.isAvailable = Objects.requireNonNull(builder.isAvailable);
        this.domainAwarenessLevel = Objects.requireNonNull(builder.domainAwarenessLevel);
        this.enabledFeatureList = Objects.requireNonNull(builder.enabledFeatureList);
    }


    public static class Builder {
        private String name;
        private String uuid;
        private String operationMode;

        private Boolean isAvailable;
        private String state;
        private List<Hypervisor> nodes;
        private String domainAwarenessLevel;
        private List<String> enabledFeatureList;

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

        public Cluster.Builder withState(final String state) {
            this.state = state;
            return this;
        }

        public Cluster.Builder withOperationMode(final String operationMode) {
            this.operationMode = operationMode;
            return this;
        }

        public Cluster.Builder withNodes(List<Hypervisor> nodes) {
            this.nodes = nodes;
            return this;
        }


        public Cluster.Builder withIsAvailable(Boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Cluster.Builder withDomainAwarenessLevel(String domainAwarenessLevel) {
            this.domainAwarenessLevel = domainAwarenessLevel;
            return this;
        }

        public Cluster.Builder withEnabledFeatureList(List<String> enabledFeatureList) {
            this.enabledFeatureList = enabledFeatureList;
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
