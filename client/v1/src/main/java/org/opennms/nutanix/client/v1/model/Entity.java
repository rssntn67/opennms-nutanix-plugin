
package org.opennms.nutanix.client.v1.model;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
    "vmId",
    "uuid",
    "powerState",
    "vmName",
    "guestOperatingSystem",
    "ipAddresses",
    "hypervisorType",
    "hostName",
    "hostId",
    "hostUuid",
    "containerIds",
    "containerUuids",
    "nutanixVirtualDisks",
    "nutanixVirtualDiskIds",
    "nutanixVirtualDiskUuids",
    "virtualNicIds",
    "virtualNicUuids",
    "clusterUuid",
    "virtualGpuUuids",
    "memoryCapacityInBytes",
    "memoryReservedCapacityInBytes",
    "numVCpus",
    "cpuReservedInHz",
    "numNetworkAdapters",
    "controlDomain",
    "vdiskNames",
    "vdiskFilePaths",
    "diskCapacityInBytes",
    "protectionDomainName",
    "protectionType",
    "consistencyGroupName",
    "description",
    "fingerPrintOnWrite",
    "onDiskDedup",
    "stats",
    "usageStats",
    "nutanixGuestTools",
    "gpusInUse",
    "vmType",
    "runningOnNdfs",
    "controllerVm",
    "displayable",
    "acropolisVm"
})
@Generated("jsonschema2pojo")
public class Entity {

    @JsonProperty("vmId")
    private String vmId;
    @JsonProperty("uuid")
    private String uuid;
    @JsonProperty("powerState")
    private String powerState;
    @JsonProperty("vmName")
    private String vmName;
    @JsonProperty("guestOperatingSystem")
    private Object guestOperatingSystem;
    @JsonProperty("ipAddresses")
    private List<String> ipAddresses;
    @JsonProperty("hypervisorType")
    private String hypervisorType;
    @JsonProperty("hostName")
    private Object hostName;
    @JsonProperty("hostId")
    private Object hostId;
    @JsonProperty("hostUuid")
    private Object hostUuid;
    @JsonProperty("containerIds")
    private List<String> containerIds;
    @JsonProperty("containerUuids")
    private List<String> containerUuids;
    @JsonProperty("nutanixVirtualDisks")
    private List<String> nutanixVirtualDisks;
    @JsonProperty("nutanixVirtualDiskIds")
    private List<String> nutanixVirtualDiskIds;
    @JsonProperty("nutanixVirtualDiskUuids")
    private List<String> nutanixVirtualDiskUuids;
    @JsonProperty("virtualNicIds")
    private List<String> virtualNicIds;
    @JsonProperty("virtualNicUuids")
    private List<String> virtualNicUuids;
    @JsonProperty("clusterUuid")
    private String clusterUuid;
    @JsonProperty("virtualGpuUuids")
    private Object virtualGpuUuids;
    @JsonProperty("memoryCapacityInBytes")
    private Long memoryCapacityInBytes;
    @JsonProperty("memoryReservedCapacityInBytes")
    private Long memoryReservedCapacityInBytes;
    @JsonProperty("numVCpus")
    private Integer numVCpus;
    @JsonProperty("cpuReservedInHz")
    private Object cpuReservedInHz;
    @JsonProperty("numNetworkAdapters")
    private Integer numNetworkAdapters;
    @JsonProperty("controlDomain")
    private Object controlDomain;
    @JsonProperty("vdiskNames")
    private List<String> vdiskNames;
    @JsonProperty("vdiskFilePaths")
    private List<String> vdiskFilePaths;
    @JsonProperty("diskCapacityInBytes")
    private Long diskCapacityInBytes;
    @JsonProperty("protectionDomainName")
    private Object protectionDomainName;
    @JsonProperty("protectionType")
    private String protectionType;
    @JsonProperty("consistencyGroupName")
    private Object consistencyGroupName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("fingerPrintOnWrite")
    private String fingerPrintOnWrite;
    @JsonProperty("onDiskDedup")
    private String onDiskDedup;
    @JsonProperty("stats")
    private Stats stats;
    @JsonProperty("usageStats")
    private UsageStats usageStats;
    @JsonProperty("nutanixGuestTools")
    private NutanixGuestTools nutanixGuestTools;
    @JsonProperty("gpusInUse")
    private Object gpusInUse;
    @JsonProperty("vmType")
    private String vmType;
    @JsonProperty("runningOnNdfs")
    private Boolean runningOnNdfs;
    @JsonProperty("controllerVm")
    private Boolean controllerVm;
    @JsonProperty("displayable")
    private Boolean displayable;
    @JsonProperty("acropolisVm")
    private Boolean acropolisVm;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("vmId")
    public String getVmId() {
        return vmId;
    }

    @JsonProperty("vmId")
    public void setVmId(String vmId) {
        this.vmId = vmId;
    }

    @JsonProperty("uuid")
    public String getUuid() {
        return uuid;
    }

    @JsonProperty("uuid")
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    @JsonProperty("powerState")
    public String getPowerState() {
        return powerState;
    }

    @JsonProperty("powerState")
    public void setPowerState(String powerState) {
        this.powerState = powerState;
    }

    @JsonProperty("vmName")
    public String getVmName() {
        return vmName;
    }

    @JsonProperty("vmName")
    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    @JsonProperty("guestOperatingSystem")
    public Object getGuestOperatingSystem() {
        return guestOperatingSystem;
    }

    @JsonProperty("guestOperatingSystem")
    public void setGuestOperatingSystem(Object guestOperatingSystem) {
        this.guestOperatingSystem = guestOperatingSystem;
    }

    @JsonProperty("ipAddresses")
    public List<String> getIpAddresses() {
        return ipAddresses;
    }

    @JsonProperty("ipAddresses")
    public void setIpAddresses(List<String> ipAddresses) {
        this.ipAddresses = ipAddresses;
    }

    @JsonProperty("hypervisorType")
    public String getHypervisorType() {
        return hypervisorType;
    }

    @JsonProperty("hypervisorType")
    public void setHypervisorType(String hypervisorType) {
        this.hypervisorType = hypervisorType;
    }

    @JsonProperty("hostName")
    public Object getHostName() {
        return hostName;
    }

    @JsonProperty("hostName")
    public void setHostName(Object hostName) {
        this.hostName = hostName;
    }

    @JsonProperty("hostId")
    public Object getHostId() {
        return hostId;
    }

    @JsonProperty("hostId")
    public void setHostId(Object hostId) {
        this.hostId = hostId;
    }

    @JsonProperty("hostUuid")
    public Object getHostUuid() {
        return hostUuid;
    }

    @JsonProperty("hostUuid")
    public void setHostUuid(Object hostUuid) {
        this.hostUuid = hostUuid;
    }

    @JsonProperty("containerIds")
    public List<String> getContainerIds() {
        return containerIds;
    }

    @JsonProperty("containerIds")
    public void setContainerIds(List<String> containerIds) {
        this.containerIds = containerIds;
    }

    @JsonProperty("containerUuids")
    public List<String> getContainerUuids() {
        return containerUuids;
    }

    @JsonProperty("containerUuids")
    public void setContainerUuids(List<String> containerUuids) {
        this.containerUuids = containerUuids;
    }

    @JsonProperty("nutanixVirtualDisks")
    public List<String> getNutanixVirtualDisks() {
        return nutanixVirtualDisks;
    }

    @JsonProperty("nutanixVirtualDisks")
    public void setNutanixVirtualDisks(List<String> nutanixVirtualDisks) {
        this.nutanixVirtualDisks = nutanixVirtualDisks;
    }

    @JsonProperty("nutanixVirtualDiskIds")
    public List<String> getNutanixVirtualDiskIds() {
        return nutanixVirtualDiskIds;
    }

    @JsonProperty("nutanixVirtualDiskIds")
    public void setNutanixVirtualDiskIds(List<String> nutanixVirtualDiskIds) {
        this.nutanixVirtualDiskIds = nutanixVirtualDiskIds;
    }

    @JsonProperty("nutanixVirtualDiskUuids")
    public List<String> getNutanixVirtualDiskUuids() {
        return nutanixVirtualDiskUuids;
    }

    @JsonProperty("nutanixVirtualDiskUuids")
    public void setNutanixVirtualDiskUuids(List<String> nutanixVirtualDiskUuids) {
        this.nutanixVirtualDiskUuids = nutanixVirtualDiskUuids;
    }

    @JsonProperty("virtualNicIds")
    public List<String> getVirtualNicIds() {
        return virtualNicIds;
    }

    @JsonProperty("virtualNicIds")
    public void setVirtualNicIds(List<String> virtualNicIds) {
        this.virtualNicIds = virtualNicIds;
    }

    @JsonProperty("virtualNicUuids")
    public List<String> getVirtualNicUuids() {
        return virtualNicUuids;
    }

    @JsonProperty("virtualNicUuids")
    public void setVirtualNicUuids(List<String> virtualNicUuids) {
        this.virtualNicUuids = virtualNicUuids;
    }

    @JsonProperty("clusterUuid")
    public String getClusterUuid() {
        return clusterUuid;
    }

    @JsonProperty("clusterUuid")
    public void setClusterUuid(String clusterUuid) {
        this.clusterUuid = clusterUuid;
    }

    @JsonProperty("virtualGpuUuids")
    public Object getVirtualGpuUuids() {
        return virtualGpuUuids;
    }

    @JsonProperty("virtualGpuUuids")
    public void setVirtualGpuUuids(Object virtualGpuUuids) {
        this.virtualGpuUuids = virtualGpuUuids;
    }

    @JsonProperty("memoryCapacityInBytes")
    public Long getMemoryCapacityInBytes() {
        return memoryCapacityInBytes;
    }

    @JsonProperty("memoryCapacityInBytes")
    public void setMemoryCapacityInBytes(Long memoryCapacityInBytes) {
        this.memoryCapacityInBytes = memoryCapacityInBytes;
    }

    @JsonProperty("memoryReservedCapacityInBytes")
    public Long getMemoryReservedCapacityInBytes() {
        return memoryReservedCapacityInBytes;
    }

    @JsonProperty("memoryReservedCapacityInBytes")
    public void setMemoryReservedCapacityInBytes(Long memoryReservedCapacityInBytes) {
        this.memoryReservedCapacityInBytes = memoryReservedCapacityInBytes;
    }

    @JsonProperty("numVCpus")
    public Integer getNumVCpus() {
        return numVCpus;
    }

    @JsonProperty("numVCpus")
    public void setNumVCpus(Integer numVCpus) {
        this.numVCpus = numVCpus;
    }

    @JsonProperty("cpuReservedInHz")
    public Object getCpuReservedInHz() {
        return cpuReservedInHz;
    }

    @JsonProperty("cpuReservedInHz")
    public void setCpuReservedInHz(Object cpuReservedInHz) {
        this.cpuReservedInHz = cpuReservedInHz;
    }

    @JsonProperty("numNetworkAdapters")
    public Integer getNumNetworkAdapters() {
        return numNetworkAdapters;
    }

    @JsonProperty("numNetworkAdapters")
    public void setNumNetworkAdapters(Integer numNetworkAdapters) {
        this.numNetworkAdapters = numNetworkAdapters;
    }

    @JsonProperty("controlDomain")
    public Object getControlDomain() {
        return controlDomain;
    }

    @JsonProperty("controlDomain")
    public void setControlDomain(Object controlDomain) {
        this.controlDomain = controlDomain;
    }

    @JsonProperty("vdiskNames")
    public List<String> getVdiskNames() {
        return vdiskNames;
    }

    @JsonProperty("vdiskNames")
    public void setVdiskNames(List<String> vdiskNames) {
        this.vdiskNames = vdiskNames;
    }

    @JsonProperty("vdiskFilePaths")
    public List<String> getVdiskFilePaths() {
        return vdiskFilePaths;
    }

    @JsonProperty("vdiskFilePaths")
    public void setVdiskFilePaths(List<String> vdiskFilePaths) {
        this.vdiskFilePaths = vdiskFilePaths;
    }

    @JsonProperty("diskCapacityInBytes")
    public Long getDiskCapacityInBytes() {
        return diskCapacityInBytes;
    }

    @JsonProperty("diskCapacityInBytes")
    public void setDiskCapacityInBytes(Long diskCapacityInBytes) {
        this.diskCapacityInBytes = diskCapacityInBytes;
    }

    @JsonProperty("protectionDomainName")
    public Object getProtectionDomainName() {
        return protectionDomainName;
    }

    @JsonProperty("protectionDomainName")
    public void setProtectionDomainName(Object protectionDomainName) {
        this.protectionDomainName = protectionDomainName;
    }

    @JsonProperty("protectionType")
    public String getProtectionType() {
        return protectionType;
    }

    @JsonProperty("protectionType")
    public void setProtectionType(String protectionType) {
        this.protectionType = protectionType;
    }

    @JsonProperty("consistencyGroupName")
    public Object getConsistencyGroupName() {
        return consistencyGroupName;
    }

    @JsonProperty("consistencyGroupName")
    public void setConsistencyGroupName(Object consistencyGroupName) {
        this.consistencyGroupName = consistencyGroupName;
    }

    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    @JsonProperty("fingerPrintOnWrite")
    public String getFingerPrintOnWrite() {
        return fingerPrintOnWrite;
    }

    @JsonProperty("fingerPrintOnWrite")
    public void setFingerPrintOnWrite(String fingerPrintOnWrite) {
        this.fingerPrintOnWrite = fingerPrintOnWrite;
    }

    @JsonProperty("onDiskDedup")
    public String getOnDiskDedup() {
        return onDiskDedup;
    }

    @JsonProperty("onDiskDedup")
    public void setOnDiskDedup(String onDiskDedup) {
        this.onDiskDedup = onDiskDedup;
    }

    @JsonProperty("stats")
    public Stats getStats() {
        return stats;
    }

    @JsonProperty("stats")
    public void setStats(Stats stats) {
        this.stats = stats;
    }

    @JsonProperty("usageStats")
    public UsageStats getUsageStats() {
        return usageStats;
    }

    @JsonProperty("usageStats")
    public void setUsageStats(UsageStats usageStats) {
        this.usageStats = usageStats;
    }

    @JsonProperty("nutanixGuestTools")
    public NutanixGuestTools getNutanixGuestTools() {
        return nutanixGuestTools;
    }

    @JsonProperty("nutanixGuestTools")
    public void setNutanixGuestTools(NutanixGuestTools nutanixGuestTools) {
        this.nutanixGuestTools = nutanixGuestTools;
    }

    @JsonProperty("gpusInUse")
    public Object getGpusInUse() {
        return gpusInUse;
    }

    @JsonProperty("gpusInUse")
    public void setGpusInUse(Object gpusInUse) {
        this.gpusInUse = gpusInUse;
    }

    @JsonProperty("vmType")
    public String getVmType() {
        return vmType;
    }

    @JsonProperty("vmType")
    public void setVmType(String vmType) {
        this.vmType = vmType;
    }

    @JsonProperty("runningOnNdfs")
    public Boolean getRunningOnNdfs() {
        return runningOnNdfs;
    }

    @JsonProperty("runningOnNdfs")
    public void setRunningOnNdfs(Boolean runningOnNdfs) {
        this.runningOnNdfs = runningOnNdfs;
    }

    @JsonProperty("controllerVm")
    public Boolean getControllerVm() {
        return controllerVm;
    }

    @JsonProperty("controllerVm")
    public void setControllerVm(Boolean controllerVm) {
        this.controllerVm = controllerVm;
    }

    @JsonProperty("displayable")
    public Boolean getDisplayable() {
        return displayable;
    }

    @JsonProperty("displayable")
    public void setDisplayable(Boolean displayable) {
        this.displayable = displayable;
    }

    @JsonProperty("acropolisVm")
    public Boolean getAcropolisVm() {
        return acropolisVm;
    }

    @JsonProperty("acropolisVm")
    public void setAcropolisVm(Boolean acropolisVm) {
        this.acropolisVm = acropolisVm;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(Entity.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vmId");
        sb.append('=');
        sb.append(((this.vmId == null)?"<null>":this.vmId));
        sb.append(',');
        sb.append("uuid");
        sb.append('=');
        sb.append(((this.uuid == null)?"<null>":this.uuid));
        sb.append(',');
        sb.append("powerState");
        sb.append('=');
        sb.append(((this.powerState == null)?"<null>":this.powerState));
        sb.append(',');
        sb.append("vmName");
        sb.append('=');
        sb.append(((this.vmName == null)?"<null>":this.vmName));
        sb.append(',');
        sb.append("guestOperatingSystem");
        sb.append('=');
        sb.append(((this.guestOperatingSystem == null)?"<null>":this.guestOperatingSystem));
        sb.append(',');
        sb.append("ipAddresses");
        sb.append('=');
        sb.append(((this.ipAddresses == null)?"<null>":this.ipAddresses));
        sb.append(',');
        sb.append("hypervisorType");
        sb.append('=');
        sb.append(((this.hypervisorType == null)?"<null>":this.hypervisorType));
        sb.append(',');
        sb.append("hostName");
        sb.append('=');
        sb.append(((this.hostName == null)?"<null>":this.hostName));
        sb.append(',');
        sb.append("hostId");
        sb.append('=');
        sb.append(((this.hostId == null)?"<null>":this.hostId));
        sb.append(',');
        sb.append("hostUuid");
        sb.append('=');
        sb.append(((this.hostUuid == null)?"<null>":this.hostUuid));
        sb.append(',');
        sb.append("containerIds");
        sb.append('=');
        sb.append(((this.containerIds == null)?"<null>":this.containerIds));
        sb.append(',');
        sb.append("containerUuids");
        sb.append('=');
        sb.append(((this.containerUuids == null)?"<null>":this.containerUuids));
        sb.append(',');
        sb.append("nutanixVirtualDisks");
        sb.append('=');
        sb.append(((this.nutanixVirtualDisks == null)?"<null>":this.nutanixVirtualDisks));
        sb.append(',');
        sb.append("nutanixVirtualDiskIds");
        sb.append('=');
        sb.append(((this.nutanixVirtualDiskIds == null)?"<null>":this.nutanixVirtualDiskIds));
        sb.append(',');
        sb.append("nutanixVirtualDiskUuids");
        sb.append('=');
        sb.append(((this.nutanixVirtualDiskUuids == null)?"<null>":this.nutanixVirtualDiskUuids));
        sb.append(',');
        sb.append("virtualNicIds");
        sb.append('=');
        sb.append(((this.virtualNicIds == null)?"<null>":this.virtualNicIds));
        sb.append(',');
        sb.append("virtualNicUuids");
        sb.append('=');
        sb.append(((this.virtualNicUuids == null)?"<null>":this.virtualNicUuids));
        sb.append(',');
        sb.append("clusterUuid");
        sb.append('=');
        sb.append(((this.clusterUuid == null)?"<null>":this.clusterUuid));
        sb.append(',');
        sb.append("virtualGpuUuids");
        sb.append('=');
        sb.append(((this.virtualGpuUuids == null)?"<null>":this.virtualGpuUuids));
        sb.append(',');
        sb.append("memoryCapacityInBytes");
        sb.append('=');
        sb.append(((this.memoryCapacityInBytes == null)?"<null>":this.memoryCapacityInBytes));
        sb.append(',');
        sb.append("memoryReservedCapacityInBytes");
        sb.append('=');
        sb.append(((this.memoryReservedCapacityInBytes == null)?"<null>":this.memoryReservedCapacityInBytes));
        sb.append(',');
        sb.append("numVCpus");
        sb.append('=');
        sb.append(((this.numVCpus == null)?"<null>":this.numVCpus));
        sb.append(',');
        sb.append("cpuReservedInHz");
        sb.append('=');
        sb.append(((this.cpuReservedInHz == null)?"<null>":this.cpuReservedInHz));
        sb.append(',');
        sb.append("numNetworkAdapters");
        sb.append('=');
        sb.append(((this.numNetworkAdapters == null)?"<null>":this.numNetworkAdapters));
        sb.append(',');
        sb.append("controlDomain");
        sb.append('=');
        sb.append(((this.controlDomain == null)?"<null>":this.controlDomain));
        sb.append(',');
        sb.append("vdiskNames");
        sb.append('=');
        sb.append(((this.vdiskNames == null)?"<null>":this.vdiskNames));
        sb.append(',');
        sb.append("vdiskFilePaths");
        sb.append('=');
        sb.append(((this.vdiskFilePaths == null)?"<null>":this.vdiskFilePaths));
        sb.append(',');
        sb.append("diskCapacityInBytes");
        sb.append('=');
        sb.append(((this.diskCapacityInBytes == null)?"<null>":this.diskCapacityInBytes));
        sb.append(',');
        sb.append("protectionDomainName");
        sb.append('=');
        sb.append(((this.protectionDomainName == null)?"<null>":this.protectionDomainName));
        sb.append(',');
        sb.append("protectionType");
        sb.append('=');
        sb.append(((this.protectionType == null)?"<null>":this.protectionType));
        sb.append(',');
        sb.append("consistencyGroupName");
        sb.append('=');
        sb.append(((this.consistencyGroupName == null)?"<null>":this.consistencyGroupName));
        sb.append(',');
        sb.append("description");
        sb.append('=');
        sb.append(((this.description == null)?"<null>":this.description));
        sb.append(',');
        sb.append("fingerPrintOnWrite");
        sb.append('=');
        sb.append(((this.fingerPrintOnWrite == null)?"<null>":this.fingerPrintOnWrite));
        sb.append(',');
        sb.append("onDiskDedup");
        sb.append('=');
        sb.append(((this.onDiskDedup == null)?"<null>":this.onDiskDedup));
        sb.append(',');
        sb.append("stats");
        sb.append('=');
        sb.append(((this.stats == null)?"<null>":this.stats));
        sb.append(',');
        sb.append("usageStats");
        sb.append('=');
        sb.append(((this.usageStats == null)?"<null>":this.usageStats));
        sb.append(',');
        sb.append("nutanixGuestTools");
        sb.append('=');
        sb.append(((this.nutanixGuestTools == null)?"<null>":this.nutanixGuestTools));
        sb.append(',');
        sb.append("gpusInUse");
        sb.append('=');
        sb.append(((this.gpusInUse == null)?"<null>":this.gpusInUse));
        sb.append(',');
        sb.append("vmType");
        sb.append('=');
        sb.append(((this.vmType == null)?"<null>":this.vmType));
        sb.append(',');
        sb.append("runningOnNdfs");
        sb.append('=');
        sb.append(((this.runningOnNdfs == null)?"<null>":this.runningOnNdfs));
        sb.append(',');
        sb.append("controllerVm");
        sb.append('=');
        sb.append(((this.controllerVm == null)?"<null>":this.controllerVm));
        sb.append(',');
        sb.append("displayable");
        sb.append('=');
        sb.append(((this.displayable == null)?"<null>":this.displayable));
        sb.append(',');
        sb.append("acropolisVm");
        sb.append('=');
        sb.append(((this.acropolisVm == null)?"<null>":this.acropolisVm));
        sb.append(',');
        sb.append("additionalProperties");
        sb.append('=');
        sb.append(((this.additionalProperties == null)?"<null>":this.additionalProperties));
        sb.append(',');
        if (sb.charAt((sb.length()- 1)) == ',') {
            sb.setCharAt((sb.length()- 1), ']');
        } else {
            sb.append(']');
        }
        return sb.toString();
    }

    @Override
    public int hashCode() {
        int result = 1;
        result = ((result* 31)+((this.controllerVm == null)? 0 :this.controllerVm.hashCode()));
        result = ((result* 31)+((this.hostName == null)? 0 :this.hostName.hashCode()));
        result = ((result* 31)+((this.vmId == null)? 0 :this.vmId.hashCode()));
        result = ((result* 31)+((this.memoryCapacityInBytes == null)? 0 :this.memoryCapacityInBytes.hashCode()));
        result = ((result* 31)+((this.nutanixGuestTools == null)? 0 :this.nutanixGuestTools.hashCode()));
        result = ((result* 31)+((this.description == null)? 0 :this.description.hashCode()));
        result = ((result* 31)+((this.uuid == null)? 0 :this.uuid.hashCode()));
        result = ((result* 31)+((this.containerIds == null)? 0 :this.containerIds.hashCode()));
        result = ((result* 31)+((this.virtualNicIds == null)? 0 :this.virtualNicIds.hashCode()));
        result = ((result* 31)+((this.virtualNicUuids == null)? 0 :this.virtualNicUuids.hashCode()));
        result = ((result* 31)+((this.controlDomain == null)? 0 :this.controlDomain.hashCode()));
        result = ((result* 31)+((this.vmType == null)? 0 :this.vmType.hashCode()));
        result = ((result* 31)+((this.powerState == null)? 0 :this.powerState.hashCode()));
        result = ((result* 31)+((this.onDiskDedup == null)? 0 :this.onDiskDedup.hashCode()));
        result = ((result* 31)+((this.virtualGpuUuids == null)? 0 :this.virtualGpuUuids.hashCode()));
        result = ((result* 31)+((this.hostUuid == null)? 0 :this.hostUuid.hashCode()));
        result = ((result* 31)+((this.stats == null)? 0 :this.stats.hashCode()));
        result = ((result* 31)+((this.nutanixVirtualDiskUuids == null)? 0 :this.nutanixVirtualDiskUuids.hashCode()));
        result = ((result* 31)+((this.numNetworkAdapters == null)? 0 :this.numNetworkAdapters.hashCode()));
        result = ((result* 31)+((this.vdiskNames == null)? 0 :this.vdiskNames.hashCode()));
        result = ((result* 31)+((this.fingerPrintOnWrite == null)? 0 :this.fingerPrintOnWrite.hashCode()));
        result = ((result* 31)+((this.usageStats == null)? 0 :this.usageStats.hashCode()));
        result = ((result* 31)+((this.clusterUuid == null)? 0 :this.clusterUuid.hashCode()));
        result = ((result* 31)+((this.cpuReservedInHz == null)? 0 :this.cpuReservedInHz.hashCode()));
        result = ((result* 31)+((this.gpusInUse == null)? 0 :this.gpusInUse.hashCode()));
        result = ((result* 31)+((this.vmName == null)? 0 :this.vmName.hashCode()));
        result = ((result* 31)+((this.protectionType == null)? 0 :this.protectionType.hashCode()));
        result = ((result* 31)+((this.consistencyGroupName == null)? 0 :this.consistencyGroupName.hashCode()));
        result = ((result* 31)+((this.containerUuids == null)? 0 :this.containerUuids.hashCode()));
        result = ((result* 31)+((this.protectionDomainName == null)? 0 :this.protectionDomainName.hashCode()));
        result = ((result* 31)+((this.displayable == null)? 0 :this.displayable.hashCode()));
        result = ((result* 31)+((this.hostId == null)? 0 :this.hostId.hashCode()));
        result = ((result* 31)+((this.nutanixVirtualDisks == null)? 0 :this.nutanixVirtualDisks.hashCode()));
        result = ((result* 31)+((this.vdiskFilePaths == null)? 0 :this.vdiskFilePaths.hashCode()));
        result = ((result* 31)+((this.hypervisorType == null)? 0 :this.hypervisorType.hashCode()));
        result = ((result* 31)+((this.numVCpus == null)? 0 :this.numVCpus.hashCode()));
        result = ((result* 31)+((this.nutanixVirtualDiskIds == null)? 0 :this.nutanixVirtualDiskIds.hashCode()));
        result = ((result* 31)+((this.runningOnNdfs == null)? 0 :this.runningOnNdfs.hashCode()));
        result = ((result* 31)+((this.diskCapacityInBytes == null)? 0 :this.diskCapacityInBytes.hashCode()));
        result = ((result* 31)+((this.guestOperatingSystem == null)? 0 :this.guestOperatingSystem.hashCode()));
        result = ((result* 31)+((this.ipAddresses == null)? 0 :this.ipAddresses.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.memoryReservedCapacityInBytes == null)? 0 :this.memoryReservedCapacityInBytes.hashCode()));
        result = ((result* 31)+((this.acropolisVm == null)? 0 :this.acropolisVm.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Entity) == false) {
            return false;
        }
        Entity rhs = ((Entity) other);
        return (((((((((((((((((((((((((((((((((((((((((((((this.controllerVm == rhs.controllerVm)||((this.controllerVm!= null)&&this.controllerVm.equals(rhs.controllerVm)))&&((this.hostName == rhs.hostName)||((this.hostName!= null)&&this.hostName.equals(rhs.hostName))))&&((this.vmId == rhs.vmId)||((this.vmId!= null)&&this.vmId.equals(rhs.vmId))))&&((this.memoryCapacityInBytes == rhs.memoryCapacityInBytes)||((this.memoryCapacityInBytes!= null)&&this.memoryCapacityInBytes.equals(rhs.memoryCapacityInBytes))))&&((this.nutanixGuestTools == rhs.nutanixGuestTools)||((this.nutanixGuestTools!= null)&&this.nutanixGuestTools.equals(rhs.nutanixGuestTools))))&&((this.description == rhs.description)||((this.description!= null)&&this.description.equals(rhs.description))))&&((this.uuid == rhs.uuid)||((this.uuid!= null)&&this.uuid.equals(rhs.uuid))))&&((this.containerIds == rhs.containerIds)||((this.containerIds!= null)&&this.containerIds.equals(rhs.containerIds))))&&((this.virtualNicIds == rhs.virtualNicIds)||((this.virtualNicIds!= null)&&this.virtualNicIds.equals(rhs.virtualNicIds))))&&((this.virtualNicUuids == rhs.virtualNicUuids)||((this.virtualNicUuids!= null)&&this.virtualNicUuids.equals(rhs.virtualNicUuids))))&&((this.controlDomain == rhs.controlDomain)||((this.controlDomain!= null)&&this.controlDomain.equals(rhs.controlDomain))))&&((this.vmType == rhs.vmType)||((this.vmType!= null)&&this.vmType.equals(rhs.vmType))))&&((this.powerState == rhs.powerState)||((this.powerState!= null)&&this.powerState.equals(rhs.powerState))))&&((this.onDiskDedup == rhs.onDiskDedup)||((this.onDiskDedup!= null)&&this.onDiskDedup.equals(rhs.onDiskDedup))))&&((this.virtualGpuUuids == rhs.virtualGpuUuids)||((this.virtualGpuUuids!= null)&&this.virtualGpuUuids.equals(rhs.virtualGpuUuids))))&&((this.hostUuid == rhs.hostUuid)||((this.hostUuid!= null)&&this.hostUuid.equals(rhs.hostUuid))))&&((this.stats == rhs.stats)||((this.stats!= null)&&this.stats.equals(rhs.stats))))&&((this.nutanixVirtualDiskUuids == rhs.nutanixVirtualDiskUuids)||((this.nutanixVirtualDiskUuids!= null)&&this.nutanixVirtualDiskUuids.equals(rhs.nutanixVirtualDiskUuids))))&&((this.numNetworkAdapters == rhs.numNetworkAdapters)||((this.numNetworkAdapters!= null)&&this.numNetworkAdapters.equals(rhs.numNetworkAdapters))))&&((this.vdiskNames == rhs.vdiskNames)||((this.vdiskNames!= null)&&this.vdiskNames.equals(rhs.vdiskNames))))&&((this.fingerPrintOnWrite == rhs.fingerPrintOnWrite)||((this.fingerPrintOnWrite!= null)&&this.fingerPrintOnWrite.equals(rhs.fingerPrintOnWrite))))&&((this.usageStats == rhs.usageStats)||((this.usageStats!= null)&&this.usageStats.equals(rhs.usageStats))))&&((this.clusterUuid == rhs.clusterUuid)||((this.clusterUuid!= null)&&this.clusterUuid.equals(rhs.clusterUuid))))&&((this.cpuReservedInHz == rhs.cpuReservedInHz)||((this.cpuReservedInHz!= null)&&this.cpuReservedInHz.equals(rhs.cpuReservedInHz))))&&((this.gpusInUse == rhs.gpusInUse)||((this.gpusInUse!= null)&&this.gpusInUse.equals(rhs.gpusInUse))))&&((this.vmName == rhs.vmName)||((this.vmName!= null)&&this.vmName.equals(rhs.vmName))))&&((this.protectionType == rhs.protectionType)||((this.protectionType!= null)&&this.protectionType.equals(rhs.protectionType))))&&((this.consistencyGroupName == rhs.consistencyGroupName)||((this.consistencyGroupName!= null)&&this.consistencyGroupName.equals(rhs.consistencyGroupName))))&&((this.containerUuids == rhs.containerUuids)||((this.containerUuids!= null)&&this.containerUuids.equals(rhs.containerUuids))))&&((this.protectionDomainName == rhs.protectionDomainName)||((this.protectionDomainName!= null)&&this.protectionDomainName.equals(rhs.protectionDomainName))))&&((this.displayable == rhs.displayable)||((this.displayable!= null)&&this.displayable.equals(rhs.displayable))))&&((this.hostId == rhs.hostId)||((this.hostId!= null)&&this.hostId.equals(rhs.hostId))))&&((this.nutanixVirtualDisks == rhs.nutanixVirtualDisks)||((this.nutanixVirtualDisks!= null)&&this.nutanixVirtualDisks.equals(rhs.nutanixVirtualDisks))))&&((this.vdiskFilePaths == rhs.vdiskFilePaths)||((this.vdiskFilePaths!= null)&&this.vdiskFilePaths.equals(rhs.vdiskFilePaths))))&&((this.hypervisorType == rhs.hypervisorType)||((this.hypervisorType!= null)&&this.hypervisorType.equals(rhs.hypervisorType))))&&((this.numVCpus == rhs.numVCpus)||((this.numVCpus!= null)&&this.numVCpus.equals(rhs.numVCpus))))&&((this.nutanixVirtualDiskIds == rhs.nutanixVirtualDiskIds)||((this.nutanixVirtualDiskIds!= null)&&this.nutanixVirtualDiskIds.equals(rhs.nutanixVirtualDiskIds))))&&((this.runningOnNdfs == rhs.runningOnNdfs)||((this.runningOnNdfs!= null)&&this.runningOnNdfs.equals(rhs.runningOnNdfs))))&&((this.diskCapacityInBytes == rhs.diskCapacityInBytes)||((this.diskCapacityInBytes!= null)&&this.diskCapacityInBytes.equals(rhs.diskCapacityInBytes))))&&((this.guestOperatingSystem == rhs.guestOperatingSystem)||((this.guestOperatingSystem!= null)&&this.guestOperatingSystem.equals(rhs.guestOperatingSystem))))&&((this.ipAddresses == rhs.ipAddresses)||((this.ipAddresses!= null)&&this.ipAddresses.equals(rhs.ipAddresses))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.memoryReservedCapacityInBytes == rhs.memoryReservedCapacityInBytes)||((this.memoryReservedCapacityInBytes!= null)&&this.memoryReservedCapacityInBytes.equals(rhs.memoryReservedCapacityInBytes))))&&((this.acropolisVm == rhs.acropolisVm)||((this.acropolisVm!= null)&&this.acropolisVm.equals(rhs.acropolisVm))));
    }

}
