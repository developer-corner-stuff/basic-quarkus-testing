package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.BookSeller;
import org.acme.service.BookSellerService;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Set;

@Path("/bookseller")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookSellerResource {

    @Inject
    BookSellerService bookSellerService;

    @GET
    @Path("/")
    public Set getBookSellers(@QueryParam("query") String query) {
        return bookSellerService.find(query);
    }

    @GET
    @Path("/{id}")
    public BookSeller getBookSellerById(@PathParam("id") Long id) {
        return bookSellerService.findById(id);
    }

    @POST
    @Path("/")
    public Response create(BookSeller bookSeller) {
        bookSellerService.create(bookSeller);
        return Response.ok(bookSeller).status(201).build();
    }

    @PUT
    @Path("/{id}")
    public Response update(@PathParam("id") Long bookSellerId, BookSeller bookSeller) {
        try {
            return Response.ok(bookSellerService.update(bookSellerId, bookSeller)).build();
        } catch (Exception ex) {
            if (ex instanceof InvalidParameterException){
                return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", ex.getMessage())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response remove(@PathParam("id") Long bookSellerId) {
        var isRemoved = bookSellerService.remove(bookSellerId);
        if (!isRemoved) {
            return Response.notModified().build();
        }

        return Response.noContent().build();
    }

}
