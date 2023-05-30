package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class Alert {
    public final String uuid;
    public final String severity;
    public final String type;

    private Alert(Builder builder) {
        this.uuid = Objects.requireNonNull(builder.uuid);
        this.severity = Objects.requireNonNull(builder.severity);
        this.type = Objects.requireNonNull(builder.type);
    }

    public static class Builder {
        private String uuid;
        private String severity;
        private String type;

        private Builder() {
        }

        public Alert.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Alert.Builder withSeverity(final String severity) {
            this.severity = severity;
            return this;
        }

        public Alert.Builder withType(final String type) {
            this.type = type;
            return this;
        }

        public Alert build() {
            return new Alert(this);
        }

    }
    public static Alert.Builder builder() {
        return new Alert.Builder();
    }
}
