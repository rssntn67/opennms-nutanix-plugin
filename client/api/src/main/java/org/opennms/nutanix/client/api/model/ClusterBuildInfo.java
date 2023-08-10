package org.opennms.nutanix.client.api.model;

import java.time.OffsetDateTime;

public class ClusterBuildInfo {
    public final String commitId;
    public final String fullVersion;

    public final OffsetDateTime commitDate;

    public final boolean isLongTermSupport;

    public final String version;

    public final String shortCommitId;

    public final String buildType;

    public ClusterBuildInfo(Builder builder) {
        this.commitId = builder.commitId;
        this.fullVersion = builder.fullVersion;
        this.commitDate = builder.commitDate;
        this.isLongTermSupport = builder.isLongTermSupport;
        this.version = builder.version;
        this.shortCommitId = builder.shortCommitId;
        this.buildType = builder.buildType;
    }


    public static class Builder {

        private String commitId;
        private String fullVersion;
        private OffsetDateTime commitDate;
        private boolean isLongTermSupport;
        private String version;
        private String shortCommitId;
        private String buildType;

        public Builder withCommitId(String commitId) {
            this.commitId = commitId;
            return this;
        }

        public Builder withFullVersion(String fullVersion) {
            this.fullVersion = fullVersion;
            return this;
        }

        public Builder withCommitDate(OffsetDateTime commitDate) {
            this.commitDate = commitDate;
            return this;
        }

        public Builder withIsLongTermSupport(boolean isLongTermSupport) {
            this.isLongTermSupport = isLongTermSupport;
            return this;
        }

        public Builder withVersion(String version) {
            this.version = version;
            return this;
        }

        public Builder withShortCommitId(String shortCommitId) {
            this.shortCommitId = shortCommitId;
            return this;
        }

        public Builder withBuildType(String buildType) {
            this.buildType = buildType;
            return this;
        }

        public ClusterBuildInfo build() {
            return new ClusterBuildInfo(this);
        }
    }

    public static ClusterBuildInfo.Builder builder() {
        return new ClusterBuildInfo.Builder();
    }
}
