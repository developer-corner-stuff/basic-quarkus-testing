package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.Book;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(BookStoreResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookStoreResourceTest {

    @TestHTTPEndpoint(BookStoreResource.class)
    @TestHTTPResource("book")
    URL bookStoreEndpoint;

    @Test
    void getBooksAuthorShouldBeFound() throws IOException {
        assertTrue(IOUtils.toString(bookStoreEndpoint.openStream(), Charset.defaultCharset()).contains("Isaac Asimov"));
    }

    @Test
    @Order(1)
    void createBook() {
        Book newBook = new Book();
        newBook.setAuthor("William Gibson");
        newBook.setTitle("Neuromancer");
        Book book = given()
                .body(newBook)
                .contentType(ContentType.JSON)
                .when().post("/book")
                .then()
                .statusCode(201)
                .extract()
                .body().as(Book.class);
        assertNotNull(book);
        assertNotNull(book.id);
    }

    @Test
    @Order(2)
    void getBooksWithQueryParam() {
        given().contentType(ContentType.JSON).param("query", "Neuromancer")
                .then().statusCode(200)
                .body("size()", is(1))
                .body("title", hasItem("Neuromancer"))
                .body("author", hasItem("William Gibson"));
    }

    @Test
    @Order(3)
    void getBookById() {
        given().get("/book/{id}", 1L)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @Order(4)
    void getBookByIdAlt() {
        Book book = given()
                .when().get("/book/4")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Book.class);
        assertNotNull(book);
        assertEquals(4L, book.id);
    }

}