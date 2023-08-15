package org.opennms.nutanix.client.api.model;

import java.util.List;
import java.util.Objects;

public class Cluster {

    //From Cluster metadata
    public final String uuid;

    //From Cluster.status
    public final String state;
    public final String name;

    //From Cluster.status.resources
    public final List<ClusterHypervisor> nodes;

    // from Cluster Status Config
    public final List<ClusterSoftware> software;
    public final String encryptionStatus;

    public final List<String> serviceList;

    public final Integer redundancyFactor;

    public final String operationMode;


    public final String domainAwarenessLevel;
    public final List<String> enabledFeatureList;

    public final Boolean isAvailable;

    public final ClusterBuildInfo build;
    public final String timeZone;

    public final String clusterArch;

    // from Cluster Status Network
    public final String externalIp;
    public final List<ClusterHttpProxy> httpProxyList;

    public final ClusterSmtpServer smtpServer;

    public final List<String> ntpServerIpList;
    public final String externalSubnet;
    public final String externalDataServicesIp;
    public final List<String> nameServerIpList;

    public final List<ClusterHttpWhiteProxy> httpProxyWhitelist;
    
    public final String internalSubnet;

    private Cluster(Builder builder) {
        this.name = Objects.requireNonNull(builder.name);
        this.uuid = Objects.requireNonNull(builder.uuid);
        this.isAvailable = Objects.requireNonNull(builder.isAvailable);
        this.operationMode = Objects.requireNonNull(builder.operationMode);
        this.state = Objects.requireNonNull(builder.state);
        this.nodes = Objects.requireNonNull(builder.nodes);
        this.domainAwarenessLevel = Objects.requireNonNull(builder.domainAwarenessLevel);
        this.enabledFeatureList = Objects.requireNonNull(builder.enabledFeatureList);
        this.software = Objects.requireNonNull(builder.software);
        this.encryptionStatus = Objects.requireNonNull(builder.encryptionStatus);
        this.serviceList = Objects.requireNonNull(builder.serviceList);
        this.redundancyFactor = Objects.requireNonNull(builder.redundancyFactor);
        this.build = Objects.requireNonNull(builder.build);
        this.timeZone = Objects.requireNonNull(builder.timeZone);
        this.clusterArch = Objects.requireNonNull(builder.clusterArch);
        this.externalIp = Objects.requireNonNull(builder.externalIp);
        this.httpProxyList = Objects.requireNonNull(builder.httpProxyList);
        this.smtpServer = Objects.requireNonNull(builder.smtpServer);
        this.ntpServerIpList = Objects.requireNonNull(builder.ntpServerIpList);
        this.externalSubnet = Objects.requireNonNull(builder.externalSubnet);
        this.externalDataServicesIp = Objects.requireNonNull(builder.externalDataServicesIp);
        this.nameServerIpList = Objects.requireNonNull(builder.nameServerIpList);
        this.httpProxyWhitelist = Objects.requireNonNull(builder.httpProxyWhitelist);
        this.internalSubnet = Objects.requireNonNull(builder.internalSubnet);
    }


    public static class Builder {
        private String name;
        private String uuid;
        private String operationMode;

        private Boolean isAvailable;
        private String state;
        private List<ClusterHypervisor> nodes;
        private String domainAwarenessLevel;
        private List<String> enabledFeatureList;
        private List<ClusterSoftware> software;
        private String encryptionStatus;
        private List<String> serviceList;
        private Integer redundancyFactor;
        private ClusterBuildInfo build;
        private String timeZone;
        private String clusterArch;
        private String externalIp;
        private List<ClusterHttpProxy> httpProxyList;
        private ClusterSmtpServer smtpServer;
        private List<String> ntpServerIpList;
        private String externalSubnet;
        private String externalDataServicesIp;
        private List<String> nameServerIpList;
        private List<ClusterHttpWhiteProxy> httpProxyWhitelist;
        private String internalSubnet;

        private Builder() {
        }

        public Cluster.Builder withName(final String name) {
            this.name = name;
            return this;
        }

        public Cluster.Builder withUuid(final String uuid) {
            this.uuid = uuid;
            return this;
        }

        public Cluster.Builder withState(final String state) {
            this.state = state;
            return this;
        }

        public Cluster.Builder withOperationMode(final String operationMode) {
            this.operationMode = operationMode;
            return this;
        }

        public Cluster.Builder withNodes(List<ClusterHypervisor> nodes) {
            this.nodes = nodes;
            return this;
        }


        public Cluster.Builder withIsAvailable(Boolean isAvailable) {
            this.isAvailable = isAvailable;
            return this;
        }

        public Cluster.Builder withDomainAwarenessLevel(String domainAwarenessLevel) {
            this.domainAwarenessLevel = domainAwarenessLevel;
            return this;
        }

        public Cluster.Builder withEnabledFeatureList(List<String> enabledFeatureList) {
            this.enabledFeatureList = enabledFeatureList;
            return this;
        }

        public Cluster build() {
            return new Cluster(this);
        }

        public Builder withSoftware(List<ClusterSoftware> software) {
            this.software = software;
            return this;
        }

        public Builder withEncryptionStatus(String encryptionStatus) {
            this.encryptionStatus = encryptionStatus;
            return this;
        }

        public Builder withServiceList(List<String> serviceList) {
            this.serviceList = serviceList;
            return this;
        }

        public Builder withRedundancyFactor(Integer redundancyFactor) {
            this.redundancyFactor = redundancyFactor;
            return this;
        }

        public Builder withBuild(ClusterBuildInfo build) {
            this.build = build;
            return this;
        }

        public Builder withTimeZone(String timeZone) {
            this.timeZone = timeZone;
            return this;
        }

        public Builder withClusterArch(String clusterArch) {
            this.clusterArch = clusterArch;
            return this;
        }

        public Builder withExternalIp(String externalIp) {
            this.externalIp = externalIp;
            return this;
        }

        public Builder withHttpProxyList(List<ClusterHttpProxy> httpProxyList) {
            this.httpProxyList = httpProxyList;
            return this;
        }

        public Builder withSmtpServer(ClusterSmtpServer smtpServer) {
            this.smtpServer = smtpServer;
            return this;
        }

        public Builder withNtpServerIpList(List<String> ntpServerIpList) {
            this.ntpServerIpList = ntpServerIpList;
            return this;
        }

        public Builder withExternalSubnet(String externalSubnet) {
            this.externalSubnet = externalSubnet;
            return this;
        }

        public Builder withExternalDataServicesIp(String externalDataServicesIp) {
            this.externalDataServicesIp = externalDataServicesIp;
            return this;
        }

        public Builder withNameServerIpList(List<String> nameServerIpList) {
            this.nameServerIpList = nameServerIpList;
            return this;
        }

        public Builder withHttpProxyWhitelist(List<ClusterHttpWhiteProxy> httpProxyWhitelist) {
            this.httpProxyWhitelist = httpProxyWhitelist;
            return this;
        }

        public Builder withInternalSubnet(String internalSubnet) {
            this.internalSubnet = internalSubnet;
            return this;
        }

    }

    public static Builder builder() {
        return new Cluster.Builder();
    }

}
