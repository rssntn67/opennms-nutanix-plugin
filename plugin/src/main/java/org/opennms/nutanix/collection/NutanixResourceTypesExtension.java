package org.opennms.nutanix.collection;

import java.util.List;

import org.opennms.integration.api.v1.config.datacollection.ResourceType;
import org.opennms.integration.api.v1.config.datacollection.ResourceTypesExtension;
import org.opennms.integration.api.xml.ClassPathResourceTypesLoader;

public class NutanixResourceTypesExtension implements ResourceTypesExtension {

    private final ClassPathResourceTypesLoader classPathResourceTypesLoader =
            new ClassPathResourceTypesLoader(org.opennms.nutanix.collection.NutanixResourceTypesExtension.class, "nutanix-resource.xml");

    @Override
    public List<ResourceType> getResourceTypes() {
        return classPathResourceTypesLoader.getResourceTypes();
    }}
