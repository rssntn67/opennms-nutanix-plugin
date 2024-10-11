package org.opennms.nutanix.rest.dto;

/**
 *  DTO for connection information received by the API
 */
public class ConnectionDTO {
    private String alias;
    private String prismUrl;
    private String username;
    private String password;

    private Integer length;
    private Boolean ignoreSslCertificateValidation;
    private String connectionPool;


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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public String getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(String connectionPool) {
        this.connectionPool = connectionPool;
    }
}