package org.opennms.nutanix.shell.connections;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.opennms.nutanix.connections.ConnectionManager;

@Command(scope = "opennms-nutanix", name = "connection-edit", description = "Edit a connection", detailedDescription = "Edit an existing connection to a nutanix prism")
@Service
public class DeleteConnectionPoolCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Argument(name = "alias", description = "Alias", required = true)
    public String alias = null;

    @Override
    public Object execute() throws Exception {
        final var connection = this.connectionManager.getConnection(this.alias);

        if (connection.isEmpty()) {
            System.err.println("No connection with the given alias exists: " + this.alias);
            return null;
        }

        connection.get().setConnectionPool(null);

        System.err.println("updating: " + connection);

        connection.get().save();
        System.out.println("Connection updated");

        return null;
    }
}
