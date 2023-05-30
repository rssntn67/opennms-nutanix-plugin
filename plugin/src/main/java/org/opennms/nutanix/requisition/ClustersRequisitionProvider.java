package org.opennms.nutanix.requisition;

import java.util.Map;

import org.opennms.integration.api.v1.config.requisition.Requisition;
import org.opennms.integration.api.v1.dao.NodeDao;
import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.Connection;
import org.opennms.nutanix.connections.ConnectionManager;

public class ClustersRequisitionProvider extends AbstractRequisitionProvider<AbstractRequisitionProvider.Request> {

    public final static String TYPE = "nutanix-clusters";

    public ClustersRequisitionProvider(NodeDao nodeDao, ClientManager clientManager, ConnectionManager connectionManager, Class<? extends AbstractRequisitionProvider.Request> requestClass) {
        super(nodeDao, clientManager, connectionManager, requestClass);
    }

    @Override
    public String getType() {
        return TYPE;
    }


    @Override
    protected ClustersRequisitionProvider.Request createRequest(Connection connection, Map<String, String> parameters) {
        return new ClustersRequisitionProvider.Request(connection);
    }

    @Override
    protected Requisition handleRequest(AbstractRequisitionProvider<AbstractRequisitionProvider.Request>.RequestContext context) throws NutanixApiException {
        throw new NutanixApiException("Not yet implemented");
    }


    public static class Request extends AbstractRequisitionProvider.Request {

        public Request() {
        }

        public Request(final Connection connection) {
            super(connection);
        }

        @Override
        protected String getDefaultForeignSource() {
            return String.format("%s-%s", TYPE, this.getAlias());
        }
    }

}
