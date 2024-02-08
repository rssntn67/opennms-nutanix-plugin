package org.opennms.nutanix.rest.dto;

import lombok.Data;

/**
 *  DTO for connection information received by the API
 */
@Data
public class ConnectionDTO {
    private String alias;
    private String prismUrl;
    private String username;
    private String password;

    private Integer length;
    private Boolean ignoreSslCertificateValidation;
    private String connectionPool;

}