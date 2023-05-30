package org.opennms.nutanix.client.api;

import java.util.List;

import org.opennms.nutanix.client.api.model.Alert;
import org.opennms.nutanix.client.api.model.Cluster;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;

public interface NutanixApiClient {
    /**
     * Get the VMS.
     *
     * @return a list of {@link VM}s
     * @throws NutanixApiException "see message for detail"
     */
    List<VM> getVMS() throws NutanixApiException;

    /**
     * Get the Hosts.
     *
     * @return a list of {@link Host}s
     * @throws NutanixApiException "see message for detail"
     */
    List<Host> getHosts() throws NutanixApiException;

    /**
     * Get the Clusters.
     *
     * @return a list of {@link Cluster}s
     * @throws NutanixApiException "see message for detail"
     */
    List<Cluster> getClusters() throws NutanixApiException;

    /**
     * Get the Alerts.
     *
     * @return a list of {@link Alert}s
     * @throws NutanixApiException "see message for detail"
     */
    List<Alert> getAlerts() throws NutanixApiException;

}
