package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class VmDisk {
    public final String uuid;
    public final Integer deviceIndex;
    public final String deviceType;
    public final String adapterType;
    public final Integer diskSizeMib;

    public VmDisk(Builder builder) {
        this.uuid = Objects.requireNonNull(builder.uuid);
        this.deviceIndex = Objects.requireNonNull(builder.deviceIndex);
        this.deviceType = Objects.requireNonNull(builder.deviceType);
        this.adapterType = Objects.requireNonNull(builder.adapterType);
        if (Objects.isNull(builder.diskSizeMib))
            this.diskSizeMib = 0;
        else
            this.diskSizeMib = builder.diskSizeMib;

    }

    public static class Builder {

        private Builder() {
        }

        private String uuid;
        private String deviceType;
        private Integer deviceIndex;
        private String adapterType;
        private Integer diskSizeMib;

        public VmDisk.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public VmDisk.Builder withDeviceType(final String deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public VmDisk.Builder withDiskSizeMib(final Integer diskSizeMib) {
            this.diskSizeMib = diskSizeMib;
            return this;
        }

        public VmDisk.Builder withAdapterType(final String adapterType) {
            this.adapterType = adapterType;
            return this;
        }

        public VmDisk.Builder withDeviceIndex(final Integer deviceIndex) {
            this.deviceIndex = deviceIndex;
            return this;
        }

        public VmDisk build() {
            return new VmDisk(this);
        }
    }
    public static VmDisk.Builder builder() {
        return new VmDisk.Builder();
    }

}