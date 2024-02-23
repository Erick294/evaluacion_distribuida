package org.example.rest;

import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.example.db.Cliente;
import org.example.repo.ClienteRepository;

import java.util.List;

@Path("/clientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ClienteRest {

    @Inject
    ClienteRepository rep;

    //books GET
    @GET
    public List<Cliente> findAll() {
        System.out.println("**************************");

        return rep.findAll().list();
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id") Integer id) {
        var book = rep.findByIdOptional(id);
        if (book.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book.get()).build();
    }

    @POST
    public Response create(Cliente p) {
        rep.persist(p);

        return Response.status(Response.Status.CREATED.getStatusCode(), "author created").build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Integer id, Cliente clienteObj) {
        Cliente cliente = rep.findById(id);
        cliente.setNombre(clienteObj.getNombre());
        cliente.setApellido(clienteObj.getApellido());
        cliente.setDireccion(clienteObj.getDireccion());

        return Response.ok().build();
    }

    //books/{id} DELETE
    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id") Integer id) {
        rep.deleteById(id);

        return Response.ok( )
                .build();
    }
}
