package org.opennms.nutanix.client.api.model;

import java.util.List;

public class ClusterHttpProxy {
    public final String name;
    public final List<String> proxyTypeList;
    public final String ip;
    public final String fqdn;
    public final Integer port;
    public final Boolean isBackup;
    public final String ipv6;

    private ClusterHttpProxy(Builder builder) {
        this.name = builder.name;
        this.proxyTypeList = builder.proxyTypeList;
        this.ip = builder.ip;
        this.fqdn = builder.fqdn;
        this.port = builder.port;
        this.isBackup = builder.isBackup;
        this.ipv6 = builder.ipv6;
    }

    public static class Builder {

        private String name;
        private List<String> proxyTypeList;
        private String ip;
        private String fqdn;
        private Integer port;
        private Boolean isBackup;
        private String ipv6;

        public Builder withName(String name) {
            this.name = name;
            return this;
        }
        public Builder withProxyTypeList(List<String> proxyTypeList) {
            this.proxyTypeList = proxyTypeList;
            return this;
        }

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withFqdn(String fqdn) {
            this.fqdn = fqdn;
            return this;
        }

        public Builder withPort(Integer port) {
            this.port = port;
            return this;
        }

        public Builder withIsBackup(Boolean isBackup) {
            this.isBackup = isBackup;
            return this;
        }

        public Builder withIpv6(String ipv6) {
            this.ipv6 = ipv6;
            return this;
        }

        public ClusterHttpProxy build() {
            return new ClusterHttpProxy(this);
        }
    }
    
    public static ClusterHttpProxy.Builder builder() {
        return new ClusterHttpProxy.Builder();
    }
    
}
