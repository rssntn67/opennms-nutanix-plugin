package org.opennms.nutanix.shell;

import java.util.HashMap;
import java.util.Map;

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

@Command(scope = "opennms-nutanix", name = "list-hosts", description = "List Nutanix Cluster Hosts", detailedDescription = "List all Nutanix Cluster hosts")
@Service
public class ListHostCommand implements Action {

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
                .column(new Col("Uuid").maxSize(36).bold(true))
                .column(new Col("Name").maxSize(24))
                .column(new Col("HostType").maxSize(24))
                .column(new Col("ClusterName").maxSize(24))
                .column(new Col("HypervisorIp").maxSize(24));
        Map<String,String> uuidToNameClusterMap = new HashMap<>();
        for (final var cluster : client.get().getClusters()) {
            uuidToNameClusterMap.put(cluster.uuid, cluster.name);
        }

        for (final var host : client.get().getHosts()) {
            final var row = table.addRow();
            row.addContent(host.uuid);
            row.addContent(host.name);
            row.addContent(host.hostType);
            row.addContent(uuidToNameClusterMap.get(host.clusterUuid));
            row.addContent(host.hypervisorIp);
        }

        table.print(System.out, true);

        return null;
    }
}
