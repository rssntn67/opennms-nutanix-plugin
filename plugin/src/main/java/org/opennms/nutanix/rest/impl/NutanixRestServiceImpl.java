package org.opennms.nutanix.rest.impl;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import javax.ws.rs.core.Response;

import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.connections.ConnectionManager;
import org.opennms.nutanix.rest.api.NutanixRestService;
import org.opennms.nutanix.rest.dto.ClusterDTO;
import org.opennms.nutanix.rest.dto.ConnectionDTO;
import org.opennms.nutanix.rest.dto.ConnectionListElementDTO;
import org.opennms.nutanix.rest.dto.ConnectionStateDTO;
import org.opennms.nutanix.rest.dto.HostDTO;
import org.opennms.nutanix.rest.dto.VMDTO;

public class NutanixRestServiceImpl implements NutanixRestService {

    private final ConnectionManager connectionManager;

    public NutanixRestServiceImpl(final ConnectionManager connectionManager) {
        this.connectionManager = Objects.requireNonNull(connectionManager);
    }

    @Override
    public List<ClusterDTO> getClusters(final String alias) throws NutanixApiException {
        final var client = this.connectionManager.getClient(alias)
                                                 .orElseThrow(null);
        return client.getClusters().stream()
                     .map(Mapper.NUTANIX_INSTANCE::sourceToTarget)
                     .collect(Collectors.toList());
    }

    @Override
    public List<HostDTO> getHosts(final String alias) throws NutanixApiException {
        final var client = this.connectionManager.getClient(alias)
                .orElseThrow(null);
        return client.getHosts().stream()
                .map(Mapper.NUTANIX_INSTANCE::sourceToTarget)
                .collect(Collectors.toList());
    }

    @Override
    public List<VMDTO> getVms(final String alias) throws NutanixApiException {
        final var client = this.connectionManager.getClient(alias)
                .orElseThrow(null);
        return client.getVMS().stream()
                .map(Mapper.NUTANIX_INSTANCE::sourceToTarget)
                .collect(Collectors.toList());
    }

    @Override
    public List<ConnectionListElementDTO> getConnectionList() {
        return this.connectionManager.getAliases().stream()
                .map(alias -> {
                    var connection = this.connectionManager.getConnection(alias).orElseThrow();

                    final var connectionDTO = new ConnectionListElementDTO();
                    connectionDTO.setAlias(alias);
                    connectionDTO.setPrismUrl(connection.getPrismUrl());
                    connectionDTO.setUsername(connection.getUsername());
                    connectionDTO.setLength(connection.getLength());
                    connectionDTO.setIgnoreSslCertificateValidation(connection.isIgnoreSslCertificateValidation());

                    return connectionDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public Response addConnection(ConnectionDTO connectionDTO, boolean dryRun, boolean skipValidation) {
        if (this.connectionManager.getConnection(connectionDTO.getAlias()).isPresent()) {
            return Response.status(Response.Status.BAD_REQUEST)
                           .entity("Connection already exists")
                           .build();
        }

        final var connection = this.connectionManager.newConnection(
                connectionDTO.getAlias(),
                connectionDTO.getPrismUrl(),
                connectionDTO.getUsername(),
                connectionDTO.getPassword(),
                connectionDTO.getIgnoreSslCertificateValidation(),
                connectionDTO.getLength(),
                connectionDTO.getConnectionPool()
        );

        if (!skipValidation) {
            final var error = connection.validate();
            if (error.isPresent()) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity(String.format("Failed to validate credentials: %s", error.get().message))
                               .build();
            }
        }

        if (dryRun) {
            return Response.ok()
                           .entity("Dry run successful")
                           .build();
        }

        connection.save();

        return Response.ok()
                       .entity("Connection successfully added")
                       .build();

    }

    @Override
    public Response editConnection(final String alias, final ConnectionDTO connectionDTO, boolean skipValidation) {
        final var connection = this.connectionManager.getConnection(alias);
        if (connection.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No such connection exists")
                           .build();
        }

        connection.get().setPrismUrl(connectionDTO.getPrismUrl());
        connection.get().setUsername(connectionDTO.getUsername());
        connection.get().setPassword(connectionDTO.getPassword());
        connection.get().setIgnoreSslCertificateValidation(connectionDTO.getIgnoreSslCertificateValidation());
        connection.get().setLength(connectionDTO.getLength());
        connection.get().setConnectionPool(connectionDTO.getConnectionPool());

        if (!skipValidation) {
            final var error = connection.get().validate();
            if (error.isPresent()) {
                return Response.status(Response.Status.BAD_REQUEST)
                               .entity(String.format("Failed to validate credentials: %s", error.get().message))
                               .build();
            }
        }

        connection.get().save();

        return Response.ok().entity("Connection successfully updated").build();
    }

    @Override
    public Response validateConnection(final String alias) {
        final var connection = this.connectionManager.getConnection(alias);
        if (connection.isPresent()) {
            final var response = new ConnectionStateDTO();
            response.setAlias(alias);
            response.setPrismUrl(connection.get().getPrismUrl());
            response.setValid(connection.get().validate().isEmpty());

            return Response.ok()
                           .entity(response)
                           .build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                           .entity("No such connection exists")
                           .build();
        }
    }

    @Override
    public Response deleteConnection(String alias) {
        if (this.connectionManager.deleteConnection(alias)) {
            return Response.status(Response.Status.NO_CONTENT).build();
        } else {
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("No such connection exists")
                    .build();
        }
    }
}
