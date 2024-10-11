package org.opennms.nutanix.rest.dto;

public class HostDTO {
    private String uuid;
    private String name;
    private String state;

    private String controllerVmIp;

    private String hypervisorIp;

    private String clusterUuid;

    private int numVms;

    public String getUuid() {
        return uuid;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getControllerVmIp() {
        return controllerVmIp;
    }

    public void setControllerVmIp(String controllerVmIp) {
        this.controllerVmIp = controllerVmIp;
    }

    public String getHypervisorIp() {
        return hypervisorIp;
    }

    public void setHypervisorIp(String hypervisorIp) {
        this.hypervisorIp = hypervisorIp;
    }

    public String getClusterUuid() {
        return clusterUuid;
    }

    public void setClusterUuid(String clusterUuid) {
        this.clusterUuid = clusterUuid;
    }

    public int getNumVms() {
        return numVms;
    }

    public void setNumVms(int numVms) {
        this.numVms = numVms;
    }
}
