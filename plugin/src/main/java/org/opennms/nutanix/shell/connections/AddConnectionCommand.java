package org.opennms.nutanix.shell.connections;

import org.apache.karaf.shell.api.action.Action;
import org.apache.karaf.shell.api.action.Argument;
import org.apache.karaf.shell.api.action.Command;
import org.apache.karaf.shell.api.action.Option;
import org.apache.karaf.shell.api.action.lifecycle.Reference;
import org.apache.karaf.shell.api.action.lifecycle.Service;
import org.opennms.nutanix.connections.ConnectionManager;

@Command(scope = "opennms-nutanix", name = "connection-add", description = "Add a connection", detailedDescription = "Add a connection to a nutanix prism")
@Service
public class AddConnectionCommand implements Action {

    @Reference
    private ConnectionManager connectionManager;

    @Option(name = "-t", aliases = "--test", description = "Dry run mode, test the credentials but do not save them")
    boolean dryRun = false;

    @Option(name="-f", aliases="--force", description="Skip validation and save the connection as-is")
    public boolean skipValidation = false;

    @Option(name = "-i", aliases = "--ignore-ssl-certificate-validation", description = "Ignore ssl certificate validation")
    boolean ignoreSslCertificateValidation = false;


    @Argument(name = "alias", description = "Alias", required = true)
    public String alias = null;

    @Argument(index = 1, name = "url", description = "Nutanix Prism Url", required = true)
    public String url = null;

    @Argument(index = 2, name = "username", description = "Nutanix Prism API username", required = true)
    public String username = null;

    @Argument(index = 3, name = "password", description = "Nutanix Prism API password", required = true)
    public String password = null;

    @Argument(index = 4, name = "length", description = "Nutanix Prism API object retrivial length defaults to 20")
    public int length = 20;


    @Override
    public Object execute() {
        if (this.connectionManager.getConnection(this.alias).isPresent()) {
            System.err.println("Connection with alias already exists: " + this.alias);
            return null;
        }

        final var connection =
                this.connectionManager.newConnection(
                                this.alias,
                                this.url,
                                this.username,
                                this.password,
                        this.ignoreSslCertificateValidation,
                        this.length
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
