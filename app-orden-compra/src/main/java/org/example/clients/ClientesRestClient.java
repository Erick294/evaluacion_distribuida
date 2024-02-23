package org.example.clients;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
import org.example.dtos.ClienteDto;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@RegisterRestClient(configKey = "ClientesRestClient")
public interface ClientesRestClient {
    @GET
    public List<ClienteDto> findAll();

    @GET
    @Path("/{id}")
    public ClienteDto findById(@PathParam("id") Integer id);
}
