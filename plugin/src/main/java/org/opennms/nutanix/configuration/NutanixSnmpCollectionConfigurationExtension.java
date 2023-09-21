package org.opennms.nutanix.configuration;

import java.util.List;

import org.opennms.integration.api.v1.config.datacollection.SnmpCollectionExtension;
import org.opennms.integration.api.v1.config.datacollection.SnmpDataCollection;
import org.opennms.integration.api.xml.ClasspathSnmpDataCollectionLoader;

public class NutanixSnmpCollectionConfigurationExtension implements SnmpCollectionExtension {

    @Override
    public List<SnmpDataCollection> getSnmpDataCollectionGroups() {
        return new ClasspathSnmpDataCollectionLoader(
                NutanixSnmpCollectionConfigurationExtension.class, "",
                "nutanix.xml"
        ).getSnmpDataCollections();
    }

    @Override
    public String getSnmpCollectionName() {
        return "nutanix";
    }

}