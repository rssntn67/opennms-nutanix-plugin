package org.opennms.nutanix.rest.dto;

public class ConnectionStateDTO {
    private String alias;
    private String prismUrl;
    private boolean valid;

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

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }
}