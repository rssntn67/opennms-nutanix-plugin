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
import org.opennms.nutanix.connections.ConnectionManager;

@Command(scope = "opennms-nutanix", name = "list-vms", description = "List Nutanix Cluster VM", detailedDescription = "List all Nutanix Cluster Virtual Machine")
@Service
public class ListVMCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Reference
    private Session session;

    @Argument(name = "alias", description = "Connection alias", required = true)
    @Completion(AliasCompleter.class)
    private String alias = null;

    @Override
    public Object execute() throws Exception {
        final var client = this.connectionManager.getClient(this.alias);
        if (client.isEmpty()) {
            System.err.println("No connection with alias " + this.alias);
            return null;
        }

        final var table = new ShellTable()
                .size(session.getTerminal().getWidth() - 1)
                .column(new Col("Uuid").maxSize(24).bold(true))
                .column(new Col("Name").maxSize(24));

        for (final var vm : client.get().getVMS()) {
            final var row = table.addRow();
            row.addContent(vm.uuid);
            row.addContent(vm.name);
        }

        table.print(System.out, true);

        return null;
    }
}
