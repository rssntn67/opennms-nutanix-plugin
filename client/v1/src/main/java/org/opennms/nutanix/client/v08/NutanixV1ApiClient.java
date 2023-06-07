package org.opennms.nutanix.client.v08;

import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;

public class NutanixV1ApiClient implements NutanixApiClient {


    @Override
    public List<VM> getVMS() throws NutanixApiException {
        return null;
    }

    @Override
    public VM getVM(String uuid) throws NutanixApiException {
        return null;
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        return null;
    }

    @Override
    public Host getHost(String uuid) throws NutanixApiException {
        return null;
    }

    @Override
    public List<Cluster> getClusters() throws NutanixApiException {
        return null;
    }

    @Override
    public Cluster getCluster(String uuid) throws NutanixApiException {
        return null;
    }

    @Override
    public List<Alert> getAlerts() throws NutanixApiException {
        return null;
    }

    @Override
    public MetricsCluster getClusterMetric(String uuid) {
        return null;
    }


}
