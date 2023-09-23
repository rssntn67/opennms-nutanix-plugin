package org.opennms.nutanix.client.api.model;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alert {
    public final String uuid;
    public final String severity;
    public final String alertType;
    public final String message;
    public final String descr;
    public final List<Entity> affectedEntities;
    public final Boolean isResolved;

    public final OffsetDateTime creationTime;


    private Alert(Builder builder) {
        this.uuid = Objects.requireNonNull(builder.uuid);
        this.severity = Objects.requireNonNull(builder.severity);
        this.alertType = Objects.requireNonNull(builder.alertType);
        this.isResolved = Objects.requireNonNull(builder.isResolved);
        this.message = Objects.requireNonNull(builder.message);
        this.descr = Objects.requireNonNull(builder.descr);
        this.creationTime = Objects.requireNonNull(builder.creationTime);
        this.affectedEntities = builder.affectedEntity;
    }

    public static class Builder {
        private String uuid;
        private String severity;
        private String alertType;

        private String message;
        private String descr;

        private Boolean isResolved;

        private final List<Entity> affectedEntity = new ArrayList<>();

        private OffsetDateTime creationTime;

        private Builder() {
        }

        public Alert.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Alert.Builder withCreationTime(final OffsetDateTime creationTime) {
            this.creationTime =creationTime;
            return this;
        }

        public Alert.Builder withSeverity(final String severity) {
            this.severity = severity;
            return this;
        }

        public Alert.Builder withMessage(final String message) {
            this.message = message;
            return this;
        }

        public Alert.Builder withDescr(final String descr) {
            this.descr = descr;
            return this;
        }

        public Alert.Builder withIsResolved(final boolean isResolved) {
            this.isResolved = isResolved;
            return this;
        }


        public Alert.Builder withAlertType(final String alertType) {
            this.alertType = alertType;
            return this;
        }

        public void addAffectedEntity(final Entity entity) {
            this.affectedEntity.add(entity);
        }


        public Alert build() {
            return new Alert(this);
        }

    }
    public static Alert.Builder builder() {
        return new Alert.Builder();
    }
}
