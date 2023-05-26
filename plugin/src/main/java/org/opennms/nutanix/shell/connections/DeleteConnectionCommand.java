package org.opennms.nutanix.shell.connections;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.shell.AliasCompleter;

@Command(scope = "opennms-nutanix", name = "connection-delete", description = "Delete a connection", detailedDescription = "Deletes a connection to a nutanix prism")
@Service
public class DeleteConnectionCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Argument(index = 0, name = "alias", description = "Connection alias to delete", required = true, multiValued = false)
    @Completion(AliasCompleter.class)
    public String alias = null;

    @Override
    public Object execute() {
        if (this.connectionManager.deleteConnection(alias)) {
            System.out.println("Connection deleted");
        } else {
            System.out.println("Connection not found");
        }
        return null;
    }
}
