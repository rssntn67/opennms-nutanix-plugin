package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class Disk {
    public final String uuid;
    public final Integer deviceIndex;
    public final String deviceType;
    public final String adapterType;
    public final Integer diskSizeMib;

    public Disk(Builder builder) {
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

        public Disk.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Disk.Builder withDeviceType(final String deviceType) {
            this.deviceType = deviceType;
            return this;
        }

        public Disk.Builder withDiskSizeMib(final Integer diskSizeMib) {
            this.diskSizeMib = diskSizeMib;
            return this;
        }

        public Disk.Builder withAdapterType(final String adapterType) {
            this.adapterType = adapterType;
            return this;
        }

        public Disk.Builder withDeviceIndex(final Integer deviceIndex) {
            this.deviceIndex = deviceIndex;
            return this;
        }

        public Disk build() {
            return new Disk(this);
        }
    }
    public static Disk.Builder builder() {
        return new Disk.Builder();
    }

}