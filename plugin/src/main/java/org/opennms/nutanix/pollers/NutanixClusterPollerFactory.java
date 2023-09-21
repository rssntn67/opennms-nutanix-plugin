package org.opennms.nutanix.pollers;

import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NutanixClusterPollerFactory extends NutanixClusterAbstractPoller.Factory<NutanixClusterPoller> {

    private static final Logger LOG = LoggerFactory.getLogger(NutanixClusterPollerFactory.class);

    public NutanixClusterPollerFactory(final ClientManager clientManager,
                                       final ConnectionManager connectionManager) {
        super(clientManager, connectionManager, NutanixClusterPoller.class);
    }

    @Override
    protected NutanixClusterPoller createPoller(final ClientManager clientManager) {
        LOG.info("createPoller: {}", getPollerClassName());
        return new NutanixClusterPoller(clientManager);
    }
}
