package org.opennms.nutanix.rest.dto;

import lombok.Data;

@Data
public class ConnectionStateDTO {
    private String alias;
    private String prismUrl;
    private boolean valid;
}