package org.opennms.nutanix.rest.impl;

import org.mapstruct.factory.Mappers;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;
import org.opennms.nutanix.rest.dto.ClusterDTO;
import org.opennms.nutanix.rest.dto.HostDTO;
import org.opennms.nutanix.rest.dto.VMDTO;

@org.mapstruct.Mapper
public interface Mapper {
    Mapper NUTANIX_INSTANCE = Mappers.getMapper(Mapper.class);

    ClusterDTO sourceToTarget(Cluster source);
    HostDTO sourceToTarget(Host source);

    VMDTO sourceToTarget(VM source);
}
