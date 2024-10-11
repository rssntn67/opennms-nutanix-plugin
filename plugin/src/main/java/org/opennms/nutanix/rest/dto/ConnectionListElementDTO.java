package org.opennms.nutanix.rest.dto;

public class ConnectionListElementDTO {
    private String alias;
    private String prismUrl;
    private String username;
    private Integer length;
    private Boolean ignoreSslCertificateValidation;

    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }

    public String getPrismUrl() {
        return prismUrl;
    }

    public void setPrismUrl(String prismUrl) {
        this.prismUrl = prismUrl;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Boolean getIgnoreSslCertificateValidation() {
        return ignoreSslCertificateValidation;
    }

    public void setIgnoreSslCertificateValidation(Boolean ignoreSslCertificateValidation) {
        this.ignoreSslCertificateValidation = ignoreSslCertificateValidation;
    }
}
