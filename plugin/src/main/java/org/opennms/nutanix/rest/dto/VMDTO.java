package org.opennms.nutanix.rest.dto;

import lombok.Data;

@Data
public class VMDTO {
    private String uuid;
    private String name;
    private String state;

    private String hostUuid;
    private String hostName;

    private String clusterUuid;
    private String clusterName;

    private String powerState;

}
