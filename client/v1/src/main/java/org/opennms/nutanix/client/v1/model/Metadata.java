
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
    "grandTotalEntities",
    "totalEntities",
    "filterCriteria",
    "sortCriteria",
    "page",
    "count",
    "startIndex",
    "endIndex"
})
@Generated("jsonschema2pojo")
public class Metadata {

    @JsonProperty("grandTotalEntities")
    private Integer grandTotalEntities;
    @JsonProperty("totalEntities")
    private Integer totalEntities;
    @JsonProperty("filterCriteria")
    private String filterCriteria;
    @JsonProperty("sortCriteria")
    private String sortCriteria;
    @JsonProperty("page")
    private Integer page;
    @JsonProperty("count")
    private Integer count;
    @JsonProperty("startIndex")
    private Integer startIndex;
    @JsonProperty("endIndex")
    private Integer endIndex;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new LinkedHashMap<String, Object>();

    @JsonProperty("grandTotalEntities")
    public Integer getGrandTotalEntities() {
        return grandTotalEntities;
    }

    @JsonProperty("grandTotalEntities")
    public void setGrandTotalEntities(Integer grandTotalEntities) {
        this.grandTotalEntities = grandTotalEntities;
    }

    @JsonProperty("totalEntities")
    public Integer getTotalEntities() {
        return totalEntities;
    }

    @JsonProperty("totalEntities")
    public void setTotalEntities(Integer totalEntities) {
        this.totalEntities = totalEntities;
    }

    @JsonProperty("filterCriteria")
    public String getFilterCriteria() {
        return filterCriteria;
    }

    @JsonProperty("filterCriteria")
    public void setFilterCriteria(String filterCriteria) {
        this.filterCriteria = filterCriteria;
    }

    @JsonProperty("sortCriteria")
    public String getSortCriteria() {
        return sortCriteria;
    }

    @JsonProperty("sortCriteria")
    public void setSortCriteria(String sortCriteria) {
        this.sortCriteria = sortCriteria;
    }

    @JsonProperty("page")
    public Integer getPage() {
        return page;
    }

    @JsonProperty("page")
    public void setPage(Integer page) {
        this.page = page;
    }

    @JsonProperty("count")
    public Integer getCount() {
        return count;
    }

    @JsonProperty("count")
    public void setCount(Integer count) {
        this.count = count;
    }

    @JsonProperty("startIndex")
    public Integer getStartIndex() {
        return startIndex;
    }

    @JsonProperty("startIndex")
    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }

    @JsonProperty("endIndex")
    public Integer getEndIndex() {
        return endIndex;
    }

    @JsonProperty("endIndex")
    public void setEndIndex(Integer endIndex) {
        this.endIndex = endIndex;
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
        sb.append(Metadata.class.getName()).append('@').append(Integer.toHexString(System.identityHashCode(this))).append('[');
        sb.append("grandTotalEntities");
        sb.append('=');
        sb.append(((this.grandTotalEntities == null)?"<null>":this.grandTotalEntities));
        sb.append(',');
        sb.append("totalEntities");
        sb.append('=');
        sb.append(((this.totalEntities == null)?"<null>":this.totalEntities));
        sb.append(',');
        sb.append("filterCriteria");
        sb.append('=');
        sb.append(((this.filterCriteria == null)?"<null>":this.filterCriteria));
        sb.append(',');
        sb.append("sortCriteria");
        sb.append('=');
        sb.append(((this.sortCriteria == null)?"<null>":this.sortCriteria));
        sb.append(',');
        sb.append("page");
        sb.append('=');
        sb.append(((this.page == null)?"<null>":this.page));
        sb.append(',');
        sb.append("count");
        sb.append('=');
        sb.append(((this.count == null)?"<null>":this.count));
        sb.append(',');
        sb.append("startIndex");
        sb.append('=');
        sb.append(((this.startIndex == null)?"<null>":this.startIndex));
        sb.append(',');
        sb.append("endIndex");
        sb.append('=');
        sb.append(((this.endIndex == null)?"<null>":this.endIndex));
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
        result = ((result* 31)+((this.filterCriteria == null)? 0 :this.filterCriteria.hashCode()));
        result = ((result* 31)+((this.startIndex == null)? 0 :this.startIndex.hashCode()));
        result = ((result* 31)+((this.grandTotalEntities == null)? 0 :this.grandTotalEntities.hashCode()));
        result = ((result* 31)+((this.endIndex == null)? 0 :this.endIndex.hashCode()));
        result = ((result* 31)+((this.count == null)? 0 :this.count.hashCode()));
        result = ((result* 31)+((this.page == null)? 0 :this.page.hashCode()));
        result = ((result* 31)+((this.additionalProperties == null)? 0 :this.additionalProperties.hashCode()));
        result = ((result* 31)+((this.sortCriteria == null)? 0 :this.sortCriteria.hashCode()));
        result = ((result* 31)+((this.totalEntities == null)? 0 :this.totalEntities.hashCode()));
        return result;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if ((other instanceof Metadata) == false) {
            return false;
        }
        Metadata rhs = ((Metadata) other);
        return ((((((((((this.filterCriteria == rhs.filterCriteria)||((this.filterCriteria!= null)&&this.filterCriteria.equals(rhs.filterCriteria)))&&((this.startIndex == rhs.startIndex)||((this.startIndex!= null)&&this.startIndex.equals(rhs.startIndex))))&&((this.grandTotalEntities == rhs.grandTotalEntities)||((this.grandTotalEntities!= null)&&this.grandTotalEntities.equals(rhs.grandTotalEntities))))&&((this.endIndex == rhs.endIndex)||((this.endIndex!= null)&&this.endIndex.equals(rhs.endIndex))))&&((this.count == rhs.count)||((this.count!= null)&&this.count.equals(rhs.count))))&&((this.page == rhs.page)||((this.page!= null)&&this.page.equals(rhs.page))))&&((this.additionalProperties == rhs.additionalProperties)||((this.additionalProperties!= null)&&this.additionalProperties.equals(rhs.additionalProperties))))&&((this.sortCriteria == rhs.sortCriteria)||((this.sortCriteria!= null)&&this.sortCriteria.equals(rhs.sortCriteria))))&&((this.totalEntities == rhs.totalEntities)||((this.totalEntities!= null)&&this.totalEntities.equals(rhs.totalEntities))));
    }

}
