package org.opennms.nutanix.configuration;

import java.util.List;

import org.opennms.integration.api.v1.config.datacollection.graphs.GraphPropertiesExtension;
import org.opennms.integration.api.v1.config.datacollection.graphs.PrefabGraph;
import org.opennms.integration.api.xml.ClassPathGraphPropertiesLoader;

public class NutanixGraphPropertiesExtension implements GraphPropertiesExtension {

    private ClassPathGraphPropertiesLoader graphPropertiesLoader = new ClassPathGraphPropertiesLoader(NutanixGraphPropertiesExtension.class,
            "nutanix-graph.properties");

    @Override
    public List<PrefabGraph> getPrefabGraphs() {
        return graphPropertiesLoader.getGraphProperties();
    }
}
