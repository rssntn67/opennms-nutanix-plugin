package org.opennms.nutanix.client.api.model;

import java.util.Objects;
import java.util.Set;

public class Nic {
    public final String nicType;
    public final String kind;
    public final String name;
    public final Set<String> ipList;
    public final String macAddress;
    public final String vlanMode;
    public final Boolean isConnected;

    public Nic(Builder builder) {
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

        public Nic.Builder withNicType(String nicType) {
            this.nicType = nicType;
            return this;
        }

        private String kind;
        public Nic.Builder withKind(String kind) {
            this.kind = kind;
            return this;
        }
        private String name;
        public Nic.Builder withName(String name) {
            this.name = name;
            return this;
        }
        private Set<String> ipList;
        public Nic.Builder withIpList(Set<String> ipList) {
            this.ipList = ipList;
            return this;
        }
        private String macAddress;
        public Nic.Builder withMacAddress(String macAddress) {
            this.macAddress = macAddress;
            return this;
        }
        private String vlanMode;
        public Nic.Builder withVlanMode(String vlanMode) {
            this.vlanMode = vlanMode;
            return this;
        }
        private Boolean isConnected;
        public Nic.Builder withIsConnected(Boolean isConnected) {
            this.isConnected = isConnected;
            return this;
        }

        public Nic build() {
            return new Nic(this);
        }

    }

    public static Nic.Builder builder() {
        return new Nic.Builder();
    }
}