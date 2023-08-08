
package org.opennms.nutanix.client.v1.model;

import java.util.LinkedHashMap;
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
    "vmUuid",
    "enabled",
    "applications",
    "toolsMounted",
    "installedVersion",
    "clusterVersion",
    "vmName",
    "communicationLinkActive",
    "toRemove"
})
@Generated("jsonschema2pojo")
public class NutanixGuestTools {

    @JsonProperty("vmId")
    private String vmId;
    @JsonProperty("vmUuid")
    private String vmUuid;
    @JsonProperty("enabled")
    private Boolean enabled;
    @JsonProperty("applications")
    private Applications applications;
    @JsonProperty("toolsMounted")
    private Boolean toolsMounted;
    @JsonProperty("installedVersion")
    private Object installedVersion;
    @JsonProperty("clusterVersion")
    private Object clusterVersion;
    @JsonProperty("vmName")
    private String vmName;
    @JsonProperty("communicationLinkActive")
    private Boolean communicationLinkActive;
    @JsonProperty("toRemove")
    private Boolean toRemove;
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

    @JsonProperty("vmUuid")
    public String getVmUuid() {
        return vmUuid;
    }

    @JsonProperty("vmUuid")
    public void setVmUuid(String vmUuid) {
        this.vmUuid = vmUuid;
    }

    @JsonProperty("enabled")
    public Boolean getEnabled() {
        return enabled;
    }

    @JsonProperty("enabled")
    public void setEnabled(Boolean enabled) {
        this.enabled = enabled;
    }

    @JsonProperty("applications")
    public Applications getApplications() {
        return applications;
    }

    @JsonProperty("applications")
    public void setApplications(Applications applications) {
        this.applications = applications;
    }

    @JsonProperty("toolsMounted")
    public Boolean getToolsMounted() {
        return toolsMounted;
    }

    @JsonProperty("toolsMounted")
    public void setToolsMounted(Boolean toolsMounted) {
        this.toolsMounted = toolsMounted;
    }

    @JsonProperty("installedVersion")
    public Object getInstalledVersion() {
        return installedVersion;
    }

    @JsonProperty("installedVersion")
    public void setInstalledVersion(Object installedVersion) {
        this.installedVersion = installedVersion;
    }

    @JsonProperty("clusterVersion")
    public Object getClusterVersion() {
        return clusterVersion;
    }

    @JsonProperty("clusterVersion")
    public void setClusterVersion(Object clusterVersion) {
        this.clusterVersion = clusterVersion;
    }

    @JsonProperty("vmName")
    public String getVmName() {
        return vmName;
    }

    @JsonProperty("vmName")
    public void setVmName(String vmName) {
        this.vmName = vmName;
    }

    @JsonProperty("communicationLinkActive")
    public Boolean getCommunicationLinkActive() {
        return communicationLinkActive;
    }

    @JsonProperty("communicationLinkActive")
    public void setCommunicationLinkActive(Boolean communicationLinkActive) {
        this.communicationLinkActive = communicationLinkActive;
    }

    @JsonProperty("toRemove")
    public Boolean getToRemove() {
        return toRemove;
    }

    @JsonProperty("toRemove")
    public void setToRemove(Boolean toRemove) {
        this.toRemove = toRemove;
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
        sb.append(NutanixGuestTools.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vmId");
        sb.append('=');
        sb.append(((this.vmId == null)?"<null>":this.vmId));
        sb.append(',');
        sb.append("vmUuid");
        sb.append('=');
        sb.append(((this.vmUuid == null)?"<null>":this.vmUuid));
        sb.append(',');
        sb.append("enabled");
        sb.append('=');
        sb.append(((this.enabled == null)?"<null>":this.enabled));
        sb.append(',');
        sb.append("applications");
        sb.append('=');
        sb.append(((this.applications == null)?"<null>":this.applications));
        sb.append(',');
        sb.append("toolsMounted");
        sb.append('=');
        sb.append(((this.toolsMounted == null)?"<null>":this.toolsMounted));
        sb.append(',');
        sb.append("installedVersion");
        sb.append('=');
        sb.append(((this.installedVersion == null)?"<null>":this.installedVersion));
        sb.append(',');
        sb.append("clusterVersion");
        sb.append('=');
        sb.append(((this.clusterVersion == null)?"<null>":this.clusterVersion));
        sb.append(',');
        sb.append("vmName");
        sb.append('=');
        sb.append(((this.vmName == null)?"<null>":this.vmName));
        sb.append(',');
        sb.append("communicationLinkActive");
        sb.append('=');
        sb.append(((this.communicationLinkActive == null)?"<null>":this.communicationLinkActive));
        sb.append(',');
        sb.append("toRemove");
        sb.append('=');
        sb.append(((this.toRemove == null)?"<null>":this.toRemove));
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
        result = ((result* 31)+((this.vmName == null)? 0 :this.vmName.hashCode()));
        result = ((result* 31)+((this.toRemove == null)? 0 :this.toRemove.hashCode()));
        result = ((result* 31)+((this.vmId == null)? 0 :this.vmId.hashCode()));
        result = ((result* 31)+((this.vmUuid == null)? 0 :this.vmUuid.hashCode()));
        result = ((result* 31)+((this.communicationLinkActive == null)? 0 :this.communicationLinkActive.hashCode()));
        result = ((result* 31)+((this.toolsMounted == null)? 0 :this.toolsMounted.hashCode()));
        result = ((result* 31)+((this.installedVersion == null)? 0 :this.installedVersion.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.clusterVersion == null)? 0 :this.clusterVersion.hashCode()));
        result = ((result* 31)+((this.enabled == null)? 0 :this.enabled.hashCode()));
        result = ((result* 31)+((this.applications == null)? 0 :this.applications.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof NutanixGuestTools) == false) {
            return false;
        }
        NutanixGuestTools rhs = ((NutanixGuestTools) other);
        return ((((((((((((this.vmName == rhs.vmName)||((this.vmName!= null)&&this.vmName.equals(rhs.vmName)))&&((this.toRemove == rhs.toRemove)||((this.toRemove!= null)&&this.toRemove.equals(rhs.toRemove))))&&((this.vmId == rhs.vmId)||((this.vmId!= null)&&this.vmId.equals(rhs.vmId))))&&((this.vmUuid == rhs.vmUuid)||((this.vmUuid!= null)&&this.vmUuid.equals(rhs.vmUuid))))&&((this.communicationLinkActive == rhs.communicationLinkActive)||((this.communicationLinkActive!= null)&&this.communicationLinkActive.equals(rhs.communicationLinkActive))))&&((this.toolsMounted == rhs.toolsMounted)||((this.toolsMounted!= null)&&this.toolsMounted.equals(rhs.toolsMounted))))&&((this.installedVersion == rhs.installedVersion)||((this.installedVersion!= null)&&this.installedVersion.equals(rhs.installedVersion))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.clusterVersion == rhs.clusterVersion)||((this.clusterVersion!= null)&&this.clusterVersion.equals(rhs.clusterVersion))))&&((this.enabled == rhs.enabled)||((this.enabled!= null)&&this.enabled.equals(rhs.enabled))))&&((this.applications == rhs.applications)||((this.applications!= null)&&this.applications.equals(rhs.applications))));
    }

}
