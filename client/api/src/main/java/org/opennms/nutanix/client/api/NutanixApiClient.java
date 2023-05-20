package org.opennms.nutanix.client.api;

import java.util.List;

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

}
