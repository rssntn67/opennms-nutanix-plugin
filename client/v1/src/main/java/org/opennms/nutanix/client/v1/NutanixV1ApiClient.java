package org.opennms.nutanix.client.v1;

import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.MetricsCluster;
import org.opennms.nutanix.client.api.model.VM;

public class NutanixV1ApiClient implements NutanixApiClient {


    private final ApiClientExtention apiClient;

    public NutanixV1ApiClient(ApiClientExtention apiClient) {
        this.apiClient = apiClient;
    }
    @Override
    public List<VM> getVMS() throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public VM getVM(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public Host getHost(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public List<Cluster> getClusters() throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public Cluster getCluster(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }

    @Override
    public List<Alert> getAlerts() throws NutanixApiException {
        throw new NutanixApiException("not supported");

    }

    @Override
    public MetricsCluster getClusterMetric(String uuid) throws NutanixApiException {
        throw new NutanixApiException("not supported");
    }


}
