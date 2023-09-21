package org.opennms.nutanix.configuration;

import java.util.List;

import org.opennms.integration.api.v1.config.collector.Collector;
import org.opennms.integration.api.v1.config.collector.CollectorConfigurationExtension;
import org.opennms.integration.api.v1.config.collector.Package;
import org.opennms.integration.api.xml.ClasspathCollectorConfigurationLoader;

public class NutanixCollectorConfigurationExtension implements CollectorConfigurationExtension {

    private final CollectorConfigurationExtension collectorConfiguration = new ClasspathCollectorConfigurationLoader(
            NutanixCollectorConfigurationExtension.class, "",
            "collector-configuration.xml"
    ).getCollectorConfiguration();

    @Override
    public List<Package> getPackages() {
        return this.collectorConfiguration.getPackages();
    }

    @Override
    public List<Collector> getCollectors() {
        return this.collectorConfiguration.getCollectors();
    }
}
