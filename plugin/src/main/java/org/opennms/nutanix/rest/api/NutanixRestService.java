package org.opennms.nutanix.rest.api;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.opennms.nutanix.client.api.NutanixApiException;
import org.opennms.nutanix.rest.dto.ClusterDTO;
import org.opennms.nutanix.rest.dto.ConnectionDTO;
import org.opennms.nutanix.rest.dto.ConnectionListElementDTO;
import org.opennms.nutanix.rest.dto.HostDTO;
import org.opennms.nutanix.rest.dto.VMDTO;

@Path("/nutanix")
public interface NutanixRestService {

    @GET
    @Path("/connections")
    @Produces(value = {MediaType.APPLICATION_JSON})
    List<ConnectionListElementDTO> getConnectionList() throws NutanixApiException;

    @GET
    @Path("/clusters/{alias}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    List<ClusterDTO> getClusters(@PathParam("alias") String alias) throws NutanixApiException;

    @GET
    @Path("/hosts/{alias}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    List<HostDTO> getHosts(@PathParam("alias") String alias) throws NutanixApiException;

    @GET
    @Path("/vms/{alias}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    List<VMDTO> getVms(@PathParam("alias") String alias) throws NutanixApiException;


    @POST
    @Path("/connections")
    @Consumes({MediaType.APPLICATION_JSON})
    Response addConnection(ConnectionDTO connectionDTO, @QueryParam("dryrun") boolean dryRun, @QueryParam("force") boolean force) throws NutanixApiException;

    @PUT
    @Path("/connections/{alias}")
    @Consumes({MediaType.APPLICATION_JSON})
    Response editConnection(@PathParam("alias") String alias, ConnectionDTO connection, @QueryParam("force") boolean force) throws NutanixApiException;

    @GET
    @Path("/connections/{alias}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    Response validateConnection(@PathParam("alias") String alias);

    @DELETE
    @Path("/connections/{alias}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    Response deleteConnection(@PathParam("alias") String alias);
}
