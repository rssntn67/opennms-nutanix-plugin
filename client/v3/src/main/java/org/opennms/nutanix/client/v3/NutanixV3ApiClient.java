package org.opennms.nutanix.client.v3;

import java.util.List;

import org.opennms.nutanix.client.api.NutanixApiClient;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.client.api.model.Host;
import org.opennms.nutanix.client.api.model.VM;

public class NutanixV3ApiClient implements NutanixApiClient {
    @Override
    public List<VM> getVMS() throws NutanixApiException {
        return null;
    }

    @Override
    public List<Host> getHosts() throws NutanixApiException {
        return null;
    }
}
