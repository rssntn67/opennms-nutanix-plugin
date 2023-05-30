package org.opennms.nutanix.client.api.model;

import com.google.common.base.MoreObjects;

public class Traffic {

    private final Long bytesRx;
    private final Long bytesTx;
    private final Long packetsRx;
    private final Long packetsTx;

    private Traffic(final Builder builder) {
        this.bytesRx = builder.bytesRx;
        this.bytesTx = builder.bytesTx;
        this.packetsRx = builder.packetsRx;
        this.packetsTx = builder.packetsTx;
    }

    public static class Builder {
        private Long bytesRx;
        private Long bytesTx;
        private Long packetsRx;
        private Long packetsTx;

        public Builder withBytesRx(Long bytesRx) {
            this.bytesRx = bytesRx;
            return this;
        }

        public Builder withBytesTx(Long bytesTx) {
            this.bytesTx = bytesTx;
            return this;
        }

        public Builder withPacketsRx(Long packetsRx) {
            this.packetsRx = packetsRx;
            return this;
        }

        public Builder withPacketsTx(Long packetsTx) {
            this.packetsTx = packetsTx;
            return this;
        }

        public Traffic build() {
            return new Traffic(this);
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    public Long getBytesRx() {
        return bytesRx;
    }

    public Long getBytesTx() {
        return bytesTx;
    }

    public Long getPacketsRx() {
        return packetsRx;
    }

    public Long getPacketsTx() {
        return packetsTx;
    }

    @Override
    public String toString() {
        return MoreObjects.toStringHelper(this)
                .add("bytesRx", bytesRx)
                .add("bytesTx", bytesTx)
                .add("packetsRx", packetsRx)
                .add("packetsTx", packetsTx)
                .toString();
    }
}
