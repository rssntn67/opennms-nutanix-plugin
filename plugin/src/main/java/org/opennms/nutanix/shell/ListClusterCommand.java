package org.opennms.nutanix.shell;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Completion;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.apache.karaf.shell.api.console.Session;
import org.apache.karaf.shell.support.table.Col;
import org.apache.karaf.shell.support.table.ShellTable;
import org.opennms.nutanix.clients.ClientManager;
import org.opennms.nutanix.connections.ConnectionManager;

@Command(scope = "opennms-nutanix", name = "list-clusters", description = "List Nutanix Clusters", detailedDescription = "List all Nutanix Clusters")
@Service
public class ListClusterCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Reference
    private ClientManager clientManager;

    @Reference
    private Session session;

    @Argument(name = "alias", description = "Connection alias", required = true)
    @Completion(AliasCompleter.class)
    private String alias = null;

    @Override
    public Object execute() throws Exception {
        final var connection = this.connectionManager.getConnection(this.alias);
        if (connection.isEmpty()) {
            System.err.println("No connection with alias " + this.alias);
            return null;
        }

        final var table = new ShellTable()
                .size(session.getTerminal().getWidth() - 1)
                .column(new Col("Uuid").maxSize(36).bold(true))
                .column(new Col("Name").maxSize(24))
                .column(new Col("IsAvailable").maxSize(12))
                .column(new Col("OperationMode").maxSize(12))
                .column(new Col("ExternalIp").maxSize(24));

        final var connectionValidationError = clientManager.validate(connection.get());
        if (connectionValidationError.isPresent()) {
            System.err.println("Validation Error for connection with alias " + this.alias);
            return null;
        }

        for (final var cluster : clientManager.getClient(connection.get()).getClusters()) {
            final var row = table.addRow();
            row.addContent(cluster.uuid);
            row.addContent(cluster.name);
            row.addContent(cluster.isAvailable);
            row.addContent(cluster.operationMode);
            row.addContent(cluster.externalIp);
            row.addContent();
        }

        table.print(System.out, true);

        return null;
    }
}
