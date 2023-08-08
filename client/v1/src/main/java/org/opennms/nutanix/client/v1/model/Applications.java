
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
    "vss_snapshot",
    "file_level_restore"
})
@Generated("jsonschema2pojo")
public class Applications {

    @JsonProperty("vss_snapshot")
    private Boolean vssSnapshot;
    @JsonProperty("file_level_restore")
    private Boolean fileLevelRestore;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("vss_snapshot")
    public Boolean getVssSnapshot() {
        return vssSnapshot;
    }

    @JsonProperty("vss_snapshot")
    public void setVssSnapshot(Boolean vssSnapshot) {
        this.vssSnapshot = vssSnapshot;
    }

    @JsonProperty("file_level_restore")
    public Boolean getFileLevelRestore() {
        return fileLevelRestore;
    }

    @JsonProperty("file_level_restore")
    public void setFileLevelRestore(Boolean fileLevelRestore) {
        this.fileLevelRestore = fileLevelRestore;
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
        sb.append(Applications.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("vssSnapshot");
        sb.append('=');
        sb.append(((this.vssSnapshot == null)?"<null>":this.vssSnapshot));
        sb.append(',');
        sb.append("fileLevelRestore");
        sb.append('=');
        sb.append(((this.fileLevelRestore == null)?"<null>":this.fileLevelRestore));
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
        result = ((result* 31)+((this.vssSnapshot == null)? 0 :this.vssSnapshot.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.fileLevelRestore == null)? 0 :this.fileLevelRestore.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Applications) == false) {
            return false;
        }
        Applications rhs = ((Applications) other);
        return ((((this.vssSnapshot == rhs.vssSnapshot)||((this.vssSnapshot!= null)&&this.vssSnapshot.equals(rhs.vssSnapshot)))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.fileLevelRestore == rhs.fileLevelRestore)||((this.fileLevelRestore!= null)&&this.fileLevelRestore.equals(rhs.fileLevelRestore))));
    }

}
