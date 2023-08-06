package org.opennms.nutanix.shell.connections;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.opennms.nutanix.connections.ConnectionManager;

import com.google.common.base.Strings;

@Command(scope = "opennms-nutanix", name = "connection-edit", description = "Edit a connection", detailedDescription = "Edit an existing connection to a nutanix prism")
@Service
public class EditConnectionCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Option(name="-f", aliases="--force", description="Skip validation and save the connection as-is")
    public boolean skipValidation = false;

    @Option(name = "-a", aliases = "--apiKey", description = "Use api-key. username is ignored!")
    boolean useApiKey = false;


    @Argument(name = "alias", description = "Alias", required = true)
    public String alias = null;

    @Argument(index = 1, name = "url", description = "Nutanix Prism Url", required = true)
    public String url = null;

    @Argument(index = 2, name = "apiKey", description = "Nutanix Prism API Key", required = true, censor = true)
    public String apiKey = null;

    @Argument(index = 3, name = "username", description = "Nutanix Prism username, defaults to username", censor = true)
    public String username = null;

    @Override
    public Object execute() throws Exception {
        final var connection = this.connectionManager.getConnection(this.alias);

        if (connection.isEmpty()) {
            System.err.println("No connection with the given alias exists: " + this.alias);
            return null;
        }

        connection.get().setPrismUrl(url);
        if (useApiKey) {
            connection.get().setApiKey(apiKey);
            connection.get().setPassword(null);
            connection.get().setUsername(null);
        } else {
            connection.get().setApiKey(null);
            connection.get().setPassword(apiKey);
            if (Strings.isNullOrEmpty(username))
                connection.get().setUsername("username");
            else
                connection.get().setUsername(username);
        }
        if (!this.skipValidation) {
            final var error = connection.get().validate();
            if (error.isPresent()) {
                System.err.println("Failed to validate credentials: " + error.get().message);
                return null;
            }
        }

        connection.get().save();
        System.out.println("Connection updated");

        return null;
    }
}
