package org.opennms.nutanix.rest.dto;

import lombok.Data;

@Data
public class HostDTO {
    private String uuid;
    private String name;
    private String state;

    private String controllerVmIp;

    private String hypervisorIp;

    private String clusterUuid;

    private int numVms;
}
