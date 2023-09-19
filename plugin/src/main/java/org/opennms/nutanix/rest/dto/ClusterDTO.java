package org.opennms.nutanix.rest.dto;

import lombok.Data;

@Data
public class ClusterDTO {
    private String uuid;
    private String name;
    private String state;

    private boolean isAvailable;

    private String operationMode;

    private String externalIp;

}
