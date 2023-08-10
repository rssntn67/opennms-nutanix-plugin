package org.opennms.nutanix.client.api.model;

public class ClusterSmtpServer {
    public final String emailAddress;
    public final String type;

    public final String ip;
    public final String fqdn;
    public final Boolean isBackup;
    public final Integer port;
    public final String ipv6;

    private ClusterSmtpServer(Builder builder) {
        this.emailAddress = builder.emailAddress;
        this.type = builder.type;
        this.ip = builder.ip;
        this.fqdn = builder.fqdn;
        this.isBackup = builder.isBackup;
        this.port = builder.port;
        this.ipv6 = builder.ipv6;
    }

    public static class Builder {

        private String emailAddress;
        private String type;

        private String ip;
        private String fqdn;
        private Boolean isBackup;
        private Integer port;
        private String ipv6;

        public Builder withIp(String ip) {
            this.ip = ip;
            return this;
        }

        public Builder withEmailAddress(String emailAddress) {
            this.emailAddress = emailAddress;
            return this;
        }

        public Builder withType(String type) {
            this.type = type;
            return this;
        }

        public Builder withFqdn(String fqdn) {
            this.fqdn = fqdn;
            return this;
        }

        public Builder withIsBackup(Boolean isBackup) {
            this.isBackup = isBackup;
            return this;
        }

        public Builder withPort(Integer port) {
            this.port = port;
            return this;
        }

        public Builder withIpv6(String ipv6) {
            this.ipv6 = ipv6;
            return this;
        }

        public ClusterSmtpServer build() {
            return new ClusterSmtpServer(this);
        }
    }

    public static ClusterSmtpServer.Builder builder() {
        return new ClusterSmtpServer.Builder();
    }
    
}
