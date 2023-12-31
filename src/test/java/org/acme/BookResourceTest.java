package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.Book;
import org.acme.resource.BookResource;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItem;
import static org.hamcrest.Matchers.notNullValue;
import static org.junit.jupiter.api.Assertions.*;

@QuarkusTest
@TestHTTPEndpoint(BookResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookResourceTest {



    @Test
    void getAllBooks() {
        given().contentType(ContentType.JSON)
                .when().get("/")
                .then()
                .statusCode(200)
                .body("author", hasItems("Cixin Liu", "Isaac Asimov"));

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
                .when().post("/")
                .then()
                .statusCode(201)
                .extract()
                .body().as(Book.class);
        assertNotNull(book);
        assertNotNull(book.getId());
    }

    @Test
    void getBookByQueryParam() {
        given().contentType(ContentType.JSON).param("query", "The Three Body Problem")
                .when().get("/")
                .then().statusCode(200)
                .body("size()", is(1))
                .body("author", hasItem("Cixin Liu"));
    }

    @Test
    @Order(3)
    void updateBookById() {
        Book book = given()
                .when().get("/4")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Book.class);
        assertNotNull(book);
        assertEquals(4L, book.getId());

        book.setTitle("The Invisible Man");

        given()
                .contentType("application/json")
                .body(book)
                .when()
                .put("/4")
                .then()
                .statusCode(200);

        Book updatedBook = given()
                .when().get("/4")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Book.class);
        assertNotNull(updatedBook);
        assertEquals("The Invisible Man", book.getTitle());

    }

    @Test
    @Order(4)
    void getBookById() {
        given().get("/{id}", 1L)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @Order(5)
    void getBookByIdAlt() {
        Book book = given()
                .when().get("/3")
                .then()
                .statusCode(200)
                .extract()
                .body().as(Book.class);
        assertNotNull(book);
        assertEquals(3L, book.getId());
    }

    @Test
    @Order(6)
    void removeBookById() {
        given()
                .contentType("application/json")
                .when()
                .delete("/4")
                .then()
                .statusCode(204);
    }

}
