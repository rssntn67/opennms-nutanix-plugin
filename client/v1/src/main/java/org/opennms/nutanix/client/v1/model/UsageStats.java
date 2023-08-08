
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
    "gpu_usage_ppm",
    "framebuffer_usage_ppm"
})
@Generated("jsonschema2pojo")
public class UsageStats {

    @JsonProperty("gpu_usage_ppm")
    private String gpuUsagePpm;
    @JsonProperty("framebuffer_usage_ppm")
    private String framebufferUsagePpm;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("gpu_usage_ppm")
    public String getGpuUsagePpm() {
        return gpuUsagePpm;
    }

    @JsonProperty("gpu_usage_ppm")
    public void setGpuUsagePpm(String gpuUsagePpm) {
        this.gpuUsagePpm = gpuUsagePpm;
    }

    @JsonProperty("framebuffer_usage_ppm")
    public String getFramebufferUsagePpm() {
        return framebufferUsagePpm;
    }

    @JsonProperty("framebuffer_usage_ppm")
    public void setFramebufferUsagePpm(String framebufferUsagePpm) {
        this.framebufferUsagePpm = framebufferUsagePpm;
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
        sb.append(UsageStats.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("gpuUsagePpm");
        sb.append('=');
        sb.append(((this.gpuUsagePpm == null)?"<null>":this.gpuUsagePpm));
        sb.append(',');
        sb.append("framebufferUsagePpm");
        sb.append('=');
        sb.append(((this.framebufferUsagePpm == null)?"<null>":this.framebufferUsagePpm));
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
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.gpuUsagePpm == null)? 0 :this.gpuUsagePpm.hashCode()));
        result = ((result* 31)+((this.framebufferUsagePpm == null)? 0 :this.framebufferUsagePpm.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof UsageStats) == false) {
            return false;
        }
        UsageStats rhs = ((UsageStats) other);
        return ((((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties)))&&((this.gpuUsagePpm == rhs.gpuUsagePpm)||((this.gpuUsagePpm!= null)&&this.gpuUsagePpm.equals(rhs.gpuUsagePpm))))&&((this.framebufferUsagePpm == rhs.framebufferUsagePpm)||((this.framebufferUsagePpm!= null)&&this.framebufferUsagePpm.equals(rhs.framebufferUsagePpm))));
    }

}
