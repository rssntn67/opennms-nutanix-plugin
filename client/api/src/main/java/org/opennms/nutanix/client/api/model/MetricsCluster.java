package org.opennms.nutanix.client.api.model;

import com.google.common.base.MoreObjects;

public class MetricsCluster {
    private final Aggregate vmsCount;
    private final Long timestamp;

    public MetricsCluster(Builder builder) {

        this.vmsCount = builder.vmsCount;
        this.timestamp = builder.timestamp;
    }

    public static class Builder {
        private Aggregate vmsCount;
        private Long timestamp;

        public Builder withVmsCount(final Aggregate vmsCount) {
            this.vmsCount = vmsCount;
            return this;
        }

        public Builder withTimestamp(final Long timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public MetricsCluster build() {
            return new MetricsCluster(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("vmsCount", vmsCount)
                .add("timestamp", timestamp)
                .toString();
    }

    public Aggregate getVmsCount() {
        return vmsCount;
    }

    public Long getTimestamp() {
        return timestamp;
    }

}
