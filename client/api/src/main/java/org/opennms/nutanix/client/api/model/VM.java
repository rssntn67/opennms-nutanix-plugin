package org.opennms.nutanix.client.api.model;

import java.util.List;
import java.util.Objects;

public class VM {
    //from Status
    public final String name;
    public final String description;
    public final String state;

    //from metadata
    public final String uuid;
    public final String kind;
    public final Integer specVersion;
    public final String entityVersion;

    //from Status.Cluster
    public final String clusterUuid;
    public final String clusterName;

    //from Status.Host
    public final String hostUuid;
    public final String hostName;

    //from Status.Resources
    public final Integer numThreadsPerCore;
    public final Integer memorySizeMib;
    public final String powerState;
    public final Integer numVcpusPerSocket;
    public final Integer  numSockets;
    public final String  protectionType;
    public final String machineType;
    public final String hypervisorType;

    public final List<VMDisk> disks;
    public final List<VMNic> nics;

    private VM(Builder builder) {
        this.name=Objects.requireNonNull(builder.name);
        this.uuid=Objects.requireNonNull(builder.uuid);
        this.clusterName=Objects.requireNonNull(builder.clusterName);
        this.clusterUuid=Objects.requireNonNull(builder.clusterUuid);
        this.hostName=builder.hostName;
        this.hostUuid=builder.hostUuid;
        this.state=Objects.requireNonNull(builder.state);
        this.numThreadsPerCore=Objects.requireNonNull(builder.numThreadsPerCore);
        this.memorySizeMib=Objects.requireNonNull(builder.memorySizeMib);
        this.powerState=Objects.requireNonNull(builder.powerState);
        this.numVcpusPerSocket=Objects.requireNonNull(builder.numVcpusPerSocket);
        this.numSockets=Objects.requireNonNull(builder.numSockets);
        this.machineType=Objects.requireNonNull(builder.machineType);
        this.protectionType=Objects.requireNonNull(builder.protectionType);
        this.hypervisorType=Objects.requireNonNull(builder.hypervisorType);
        this.description=builder.description;
        this.disks=Objects.requireNonNull(builder.disks);
        this.nics=Objects.requireNonNull(builder.nics);
        this.kind=Objects.requireNonNull(builder.kind);
        this.entityVersion=Objects.requireNonNull(builder.entityVersion);
        this.specVersion=Objects.requireNonNull(builder.specVersion);
    }
    public static class Builder {
        private String name;
        private String uuid;

        private String clusterName;
        private String clusterUuid;

        private String hostUuid;
        private String hostName;

        private String state;

        private Integer numThreadsPerCore;

        public VM.Builder withNumThreadsPerCore(final Integer  numThreadsPerCore) {
            this.numThreadsPerCore = numThreadsPerCore;
            return this;
        }
        private Integer memorySizeMib;
        public VM.Builder withMemorySizeMib(final Integer  memorySizeMib) {
            this.memorySizeMib = memorySizeMib;
            return this;
        }

        private String powerState;

        public VM.Builder withPowerState(final String powerState) {
            this.powerState = powerState;
            return this;
        }

        private Integer numVcpusPerSocket;
        public VM.Builder withNumVcpusPerSocket(final Integer  numVcpusPerSocket) {
            this.numVcpusPerSocket = numVcpusPerSocket;
            return this;
        }
        private Integer numSockets;
        public VM.Builder withNumSockets(final Integer  numSockets) {
            this.numSockets = numSockets;
            return this;
        }

        private String protectionType;
        public VM.Builder withProtectionType(final String protectionType) {
            this.protectionType = protectionType;
            return this;
        }

        private String machineType;
        public VM.Builder withMachineType(final String machineType) {
            this.machineType = machineType;
            return this;
        }

        private String hypervisorType;
        public VM.Builder withHypervisorType(final String hypervisorType) {
            this.hypervisorType = hypervisorType;
            return this;
        }

        private String description;

        public VM.Builder withDescription(final String description) {
            this.description = description;
            return this;
        }

        private List<VMDisk> disks;
        public VM.Builder withDisks(final List<VMDisk> disks) {
            this.disks = disks;
            return this;
        }

        private List<VMNic> nics;
        public VM.Builder withNics(final List<VMNic> nics) {
            this.nics = nics;
            return this;
        }

        private Builder() {
        }

        public VM.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public VM.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public VM.Builder withState(final String state) {
            this.state = state;
            return this;
        }
        public VM.Builder withClusterName(final String clusterName) {
            this.clusterName = clusterName;
            return this;
        }
        public VM.Builder withClusterUuid(final String clusterUuid) {
            this.clusterUuid = clusterUuid;
            return this;
        }


        public VM.Builder withHostName(final String hostName) {
            this.hostName = hostName;
            return this;
        }
        public VM.Builder withHostUuid(final String hostUuid) {
            this.hostUuid = hostUuid;
            return this;
        }

        private String kind;
        public VM.Builder withKind(final String kind) {
            this.kind = kind;
            return this;
        }
        private Integer specVersion;
        public VM.Builder withSpecVersion(final Integer specVersion) {
            this.specVersion = specVersion;
            return this;
        }

        private String entityVersion;
        public VM.Builder withEntityVersion(final String entityVersion) {
            this.entityVersion = entityVersion;
            return this;
        }

        public VM build() {
            return new VM(this);
        }



    }

    public static Builder builder() {
        return new Builder();
    }

}
