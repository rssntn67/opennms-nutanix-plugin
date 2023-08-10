package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class ClusterHttpWhiteProxy {
    public final String target;
    public final String targetType;


    private ClusterHttpWhiteProxy(Builder builder) {
        this.target = Objects.requireNonNull(builder.target);
        this.targetType = Objects.requireNonNull(builder.targetType);
    }

    public static final class Builder {

        private String target;
        private String targetType;

        public Builder withTarget(String target) {
            this.target = target;
            return this;
        }

        public Builder withTargetType(String targetType) {
            this.targetType = targetType;
            return this;
        }

        public ClusterHttpWhiteProxy build() {
            return new ClusterHttpWhiteProxy(this);
        }
    }

    public static ClusterHttpWhiteProxy.Builder builder() {
        return new ClusterHttpWhiteProxy.Builder();
    }
}
