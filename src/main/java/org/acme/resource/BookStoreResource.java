package org.acme.resource;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Book;
import org.acme.service.BookService;

import java.security.InvalidParameterException;
import java.util.Map;
import java.util.Set;

@Path("/bookStore")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookStoreResource {

    @Inject
    BookService bookService;

    @GET
    @Path("/book")
    public Set getBooks(@QueryParam("query") String query) {
        return bookService.find(query);
    }

    @GET
    @Path("/book/{id}")
    public Book getBookById(@PathParam("id") Long id) {
        return bookService.findById(id);
    }

    @POST
    @Path("/book")
    public Response create(Book book) {
        bookService.create(book);
        return Response.ok(book).status(201).build();
    }

    @PUT
    @Path("/book/{id}")
    public Response update(@PathParam("id") Long bookId, Book book) {
        try {
            return Response.ok(bookService.update(bookId, book)).build();
        } catch (Exception ex) {
            if (ex instanceof InvalidParameterException){
                return Response.status(Response.Status.NOT_FOUND).entity(Map.of("message", ex.getMessage())).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @DELETE
    @Path("/book/{id}")
    public Response remove(@PathParam("id") Long bookId) {
        var isRemoved = bookService.remove(bookId);
        if (!isRemoved) {
            return Response.notModified().build();
        }

        return Response.noContent().build();
    }
}
