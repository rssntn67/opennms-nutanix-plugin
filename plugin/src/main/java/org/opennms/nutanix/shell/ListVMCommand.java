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

@Command(scope = "opennms-nutanix", name = "list-vms", description = "List Nutanix Cluster VM", detailedDescription = "List all Nutanix Cluster Virtual Machine")
@Service
public class ListVMCommand implements Action {

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
                .column(new Col("PowerState").maxSize(12))
                .column(new Col("ClusterName").maxSize(24))
                .column(new Col("HostName").maxSize(24));

        final var connectionValidationError = clientManager.validate(connection.get());
        if (connectionValidationError.isPresent()) {
            System.err.println("Validation Error for connection with alias " + this.alias);
            return null;
        }

        for (final var vm : clientManager.getClient(connection.get()).getVMS()) {
            final var row = table.addRow();
            row.addContent(vm.uuid);
            row.addContent(vm.name);
            row.addContent(vm.powerState);
            row.addContent(vm.clusterName);
            row.addContent(vm.hostName);
        }

        table.print(System.out, true);

        return null;
    }
}
