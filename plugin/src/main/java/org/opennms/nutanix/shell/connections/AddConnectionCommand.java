package org.opennms.nutanix.shell.connections;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.opennms.nutanix.connections.Connection;
import org.opennms.nutanix.connections.ConnectionManager;

@Command(scope = "opennms-nutanix", name = "connection-add", description = "Add a connection", detailedDescription = "Add a connection to a nutanix prism")
@Service
public class AddConnectionCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Option(name = "-t", aliases = "--test", description = "Dry run mode, test the credentials but do not save them")
    boolean dryRun = false;

    @Option(name = "-a", aliases = "--apiKey", description = "Use api-key username is ignored")
    boolean useApiKey = false;

    @Option(name="-f", aliases="--force", description="Skip validation and save the connection as-is")
    public boolean skipValidation = false;

    @Argument(name = "alias", description = "Alias", required = true)
    public String alias = null;

    @Argument(index = 1, name = "url", description = "Nutanix Prism Url", required = true)
    public String url = null;

    @Argument(index = 2, name = "apiKey", description = "Nutanix Prism API Key/Password", required = true, censor = true)
    public String apiKey = null;

    @Argument(index = 3, name = "username", description = "Nutanix Prism username", censor = true)
    public String username = null;

    @Override
    public Object execute() {
        if (this.connectionManager.getConnection(this.alias).isPresent()) {
            System.err.println("Connection with alias already exists: " + this.alias);
            return null;
        }

        final Connection connection;
        if (useApiKey)
            connection =
                    this.connectionManager.newConnection(this.alias,
                                                        this.url,
                                                        this.apiKey
            );

        else
            connection =
                    this.connectionManager.newConnection(
                            this.alias,
                            this.url,
                            this.username,
                            this.apiKey
                    );

        if (!this.skipValidation) {
            final var error = connection.validate();
            if (error.isPresent()) {
                System.err.println("Failed to validate credentials: " + error.get().message);
                return null;
            }
        }

        if (this.dryRun) {
            System.out.println("Connection valid");
            return null;
        }

        connection.save();
        System.out.println("Connection created");

        return null;
    }
}
