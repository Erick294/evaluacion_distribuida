package org.example.rest;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.transaction.UserTransaction;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.example.clients.ClientesRestClient;
import org.example.clients.ProductosRestClient;
import org.example.db.OrdenCompra;
import org.example.dtos.OrdenCompraDto;
import org.example.repo.OrdenCompraRepository;

import java.util.List;
import java.util.stream.Collectors;

@Path("/orden")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@ApplicationScoped
@Transactional
public class OrdenCompraRest {
    @Inject
    OrdenCompraRepository repo;

    @Inject
    @RestClient
    ClientesRestClient clienteClient;

    @Inject
    @RestClient
    ProductosRestClient productoClient;

    @GET
    public List<OrdenCompraDto> findAll() {
        return repo.streamAll()
                .map(orden->{
                    var cliente = clienteClient.findById(orden.getClienteId());
                    var producto = productoClient.findById(orden.getProductoId());

                    var dto = OrdenCompraDto.from(orden);

                    String cl = String.format("%s %s %s",
                            cliente.getNombre(), cliente.getApellido(), cliente.getDireccion());

                    String pr = String.format("%s %b",
                            producto.getNombre(), producto.getPrecio());

                    dto.setNombreCliente(cl);
                    dto.setProductos(pr);

                    return dto;
                })
                .collect(Collectors.toList());
    }

    @GET
    @Path("/{id}")
    public Response findById(@PathParam("id")Integer id) {
        var book = repo.findByIdOptional(id);

        if(book.isEmpty()) {
            return Response.status(Response.Status.NOT_FOUND).build();
        }

        return Response.ok(book.get()).build();
    }

    @POST
    public Response create(OrdenCompra obj) {
        obj.setId(null);

        repo.persist(obj);

        return Response.status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("/{id}")

    public Response update(@PathParam("id")Integer id, OrdenCompra obj) {

        OrdenCompra b = repo.findById(id);

        b.setPrecio(obj.getPrecio());
        b.setClienteId(obj.getClienteId());
        b.setProductoId(obj.getProductoId());

        return Response.ok()
                .build();
    }

    @DELETE
    @Path("/{id}")
    public Response delete(@PathParam("id")Integer id) {
        repo.deleteById(id);

        return Response.ok()
                .build();
    }
}
