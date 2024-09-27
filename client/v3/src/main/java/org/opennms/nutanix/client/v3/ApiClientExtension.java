package org.opennms.nutanix.client.v3;

public class ApiClientExtension extends org.opennms.nutanix.client.v3.handler.ApiClient {

    int pageSize = 30;
    boolean ignoreSslCertificateValidation = false;
    public Integer getLength() {
        return pageSize;
    }

    public void setLength(Integer length) {
        this.pageSize = length;
    }

    public void setIgnoreSslCertificateValidation(boolean b) {
        this.ignoreSslCertificateValidation = b;
    }
}
