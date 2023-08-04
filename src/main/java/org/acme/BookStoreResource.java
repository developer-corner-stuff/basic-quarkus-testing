package org.acme;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.acme.model.Book;
import org.acme.service.BookService;

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
        bookService.add(book);
        return Response.ok(book).status(201).build();
    }
}
