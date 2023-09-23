package org.opennms.nutanix.events;

import java.util.List;

import org.opennms.integration.api.v1.config.events.EventConfExtension;
import org.opennms.integration.api.v1.config.events.EventDefinition;
import org.opennms.integration.api.xml.ClasspathEventDefinitionLoader;

public class NutanixEventConfExtension implements EventConfExtension {

    private final ClasspathEventDefinitionLoader classpathEventDefinitionLoader = new ClasspathEventDefinitionLoader(
            NutanixEventConfExtension.class,
            "nutanix.events.xml", "nutanix.trap.events.xml");

    @Override
    public List<EventDefinition> getEventDefinitions() {
        return classpathEventDefinitionLoader.getEventDefinitions();
    }
}
