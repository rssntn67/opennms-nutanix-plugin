package org.opennms.nutanix.client.api.model;

import java.util.Objects;
import java.util.Set;

public class VMNic {
    public final String nicType;
    public final String kind;
    public final String name;
    public final Set<String> ipList;
    public final String macAddress;
    public final String vlanMode;
    public final Boolean isConnected;

    public VMNic(Builder builder) {
        this.nicType = Objects.requireNonNull(builder.nicType);
        this.kind = Objects.requireNonNull(builder.kind);
        this.name = Objects.requireNonNull(builder.name);
        this.ipList = Objects.requireNonNull(builder.ipList);
        this.macAddress = Objects.requireNonNull(builder.macAddress);
        this.vlanMode = Objects.requireNonNull(builder.vlanMode);
        this.isConnected = Objects.requireNonNull(builder.isConnected);
    }

    public static class Builder {

        private Builder() {
        }

        private String nicType;

        public VMNic.Builder withNicType(String nicType) {
            this.nicType = nicType;
            return this;
        }

        private String kind;
        public VMNic.Builder withKind(String kind) {
            this.kind = kind;
            return this;
        }
        private String name;
        public VMNic.Builder withName(String name) {
            this.name = name;
            return this;
        }
        private Set<String> ipList;
        public VMNic.Builder withIpList(Set<String> ipList) {
            this.ipList = ipList;
            return this;
        }
        private String macAddress;
        public VMNic.Builder withMacAddress(String macAddress) {
            this.macAddress = macAddress;
            return this;
        }
        private String vlanMode;
        public VMNic.Builder withVlanMode(String vlanMode) {
            this.vlanMode = vlanMode;
            return this;
        }
        private Boolean isConnected;
        public VMNic.Builder withIsConnected(Boolean isConnected) {
            this.isConnected = isConnected;
            return this;
        }

        public VMNic build() {
            return new VMNic(this);
        }

    }

    public static VMNic.Builder builder() {
        return new VMNic.Builder();
    }
}