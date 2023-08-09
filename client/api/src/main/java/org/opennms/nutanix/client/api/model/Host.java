package org.opennms.nutanix.client.api.model;

import java.util.Objects;

public class Host {
    public final String name;
    public final String uuid;
    public final Integer specVersion;
    public final String kind;
    public final String state;

    //
    //Controller VM is a Virtual Machine Fundamentally used for sharing data over cluster
    public final String controllerVmIp;
    public final Float oplogDiskPct;
    public final Long oplogDiskSize;

    //
    //ipMI is ip address of host hardware console
    public final String ipmi;

    // Hypervisor ip address of physical machine running AHV operating System
    public final String hypervisorIp;
    public final String hypervisorFullName;
    public final String hostType;
    public final String cpuModel;

    public final Long numCpuSockets;
    public final Long numCpuCores;
    public final Long cpuCapacityHz;
    public final String serialNumber;

    public final Long memoryCapacityMib;

    public final Long numVms;



    public final String blockSerialNumber;
    public final String blockModel;
    public final String clusterUuid;
    public final String clusterKind;

    public Host(Builder builder) {
        this.name = Objects.requireNonNull(builder.name);
        this.uuid = Objects.requireNonNull(builder.uuid);
        this.specVersion = Objects.requireNonNull(builder.specVersion);
        this.kind = Objects.requireNonNull(builder.kind);
        this.state = Objects.requireNonNull(builder.state);
        this.controllerVmIp = Objects.requireNonNull(builder.controllerVmIp);
        this.oplogDiskPct = Objects.requireNonNull(builder.oplogDiskPct);
        this.oplogDiskSize = Objects.requireNonNull(builder.oplogDiskSize);
        this.ipmi = Objects.requireNonNull(builder.ipmi);
        this.hostType = Objects.requireNonNull(builder.hostType);
        this.cpuModel = Objects.requireNonNull(builder.cpuModel);
        this.numCpuSockets = Objects.requireNonNull(builder.numCpuSockets);
        this.numCpuCores = Objects.requireNonNull(builder.numCpuCores);
        this.cpuCapacityHz = Objects.requireNonNull(builder.cpuCapacityHz);
        this.serialNumber = Objects.requireNonNull(builder.serialNumber);
        this.memoryCapacityMib = Objects.requireNonNull(builder.memoryCapacityMib);
        this.numVms = Objects.requireNonNull(builder.numVms);
        this.hypervisorIp = Objects.requireNonNull(builder.hypervisorIp);
        this.hypervisorFullName = Objects.requireNonNull(builder.hypervisorFullName);
        this.blockSerialNumber = Objects.requireNonNull(builder.blockSerialNumber);
        this.blockModel = Objects.requireNonNull(builder.blockModel);
        this.clusterUuid = Objects.requireNonNull(builder.clusterUuid);
        this.clusterKind = Objects.requireNonNull(builder.clusterKind);
    }

    public static class Builder {
        private String name;
        private String uuid;

        private Builder() {
        }

        public Host.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Host.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        private Integer specVersion;
        public Host.Builder withSpecVersion(Integer specVersion) {
            this.specVersion=specVersion;
            return this;
        }

        private String kind;
        public Host.Builder withKind(String kind) {
            this.kind=kind;
            return this;
        }
        private String state;
        public Host.Builder withState(String state) {
            this.state=state;
            return this;
        }
        private String controllerVmIp;
        public Host.Builder withControllerVmIp(String controllerVmIp) {
            this.controllerVmIp=controllerVmIp;
            return this;
        }
        private Float oplogDiskPct;

        public Host.Builder withOplogDiskPct(Float oplogDiskPct) {
            this.oplogDiskPct=oplogDiskPct;
            return this;
        }
        private Long oplogDiskSize;
        public Host.Builder withOplogDiskSize(Long oplogDiskSize) {
            this.oplogDiskSize=oplogDiskSize;
            return this;
        }
        private String ipmi;
        public Host.Builder withIpmi(String ipmi) {
            this.ipmi=ipmi;
            return this;
        }
        private String hostType;
        public Host.Builder withHostType(String hostType) {
            this.hostType=hostType;
            return this;
        }
        private String cpuModel;

        public Host.Builder withCpuModel(String cpuModel) {
            this.cpuModel=cpuModel;
            return this;
        }
        private Long numCpuSockets;
        public Host.Builder withNumCpuSockets(Long numCpuSockets) {
            this.numCpuSockets=numCpuSockets;
            return this;
        }

        private Long numCpuCores;
        public Host.Builder withNumCpuCores(Long numCpuCores) {
            this.numCpuCores=numCpuCores;
            return this;
        }

        private Long cpuCapacityHz;
        public Host.Builder withCpuCapacityHz(Long cpuCapacityHz) {
            this.cpuCapacityHz=cpuCapacityHz;
            return this;
        }
        private String serialNumber;
        public Host.Builder withSerialNumber(String serialNumber) {
            this.serialNumber=serialNumber;
            return this;
        }

        private Long memoryCapacityMib;
        public Host.Builder withMemoryCapacityMib(Long memoryCapacityMib) {
            this.memoryCapacityMib=memoryCapacityMib;
            return this;
        }

        private Long numVms;
        public Host.Builder withNumVms(Long numVms) {
            this.numVms=numVms;
            return this;
        }

        private String hypervisorIp;
        public Host.Builder withHypervisorIp(String hypervisorIp) {
            this.hypervisorIp=hypervisorIp;
            return this;
        }

        private String hypervisorFullName;
        public Host.Builder withHypervisorFullName(String hypervisorFullName) {
            this.hypervisorFullName=hypervisorFullName;
            return this;
        }

        private String blockSerialNumber;
        public Host.Builder withBlockSerialNumber(String blockSerialNumber) {
            this.blockSerialNumber=blockSerialNumber;
            return this;
        }

        private String blockModel;
        public Host.Builder withBlockModel(String blockModel) {
            this.blockModel=blockModel;
            return this;
        }
        private String clusterUuid;
        public Host.Builder withClusterUuid(String clusterUuid) {
            this.clusterUuid=clusterUuid;
            return this;
        }
        private String clusterKind;
        public Host.Builder withClusterKind(String clusterKind) {
            this.clusterKind=clusterKind;
            return this;
        }

        public Host build() {
            return new Host(this);
        }


    }

    public static Builder builder() {
        return new Host.Builder();
    }

}
