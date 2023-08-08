package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.acme.model.BookSeller;
import org.acme.resource.BookSellerResource;
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
@TestHTTPEndpoint(BookSellerResource.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BookSellerResourceTest {

    @TestHTTPEndpoint(BookSellerResource.class)
    @TestHTTPResource("")
    URL bookSellerResourceEndpoint;

    @Test
    void getBookSellerShouldBeFound() throws IOException {
        assertTrue(IOUtils.toString(bookSellerResourceEndpoint.openStream(), Charset.defaultCharset()).contains("Strand"));
    }

    @Test
    @Order(1)
    void createBookSeller() {
        BookSeller newBookSeller = new BookSeller();
        newBookSeller.setName("Strand");
        newBookSeller.setDescription("Unique East Village Bookstore");
        BookSeller bookSeller = given()
                .body(newBookSeller)
                .contentType(ContentType.JSON)
                .when().post("/")
                .then()
                .statusCode(201)
                .extract()
                .body().as(BookSeller.class);
        assertNotNull(bookSeller);
        assertNotNull(bookSeller.getId());
    }

    @Test
    @Order(2)
    void getBookSellerWithQueryParamByTitle() {
        given().contentType(ContentType.JSON).param("query", "Strand")
                .then().statusCode(200)
                .body("size()", is(1))
                .body("name", hasItem("Strand"))
                .body("Description", hasItem("Unique East Village Bookstore"));
    }

    @Test
    @Order(3)
    void updateBookSellerById() {
        BookSeller bookSeller = given()
                .when().get("/4")
                .then()
                .statusCode(200)
                .extract()
                .body().as(BookSeller.class);
        assertNotNull(bookSeller);
        assertEquals(4L, bookSeller.getId());

        bookSeller.setDescription("Indie bookstore with " + bookSeller.getDescription());

        given()
                .contentType("application/json")
                .body(bookSeller)
                .when()
                .put("/4")
                .then()
                .statusCode(200);

        BookSeller updatedBookSeller = given()
                .when().get("/4")
                .then()
                .statusCode(200)
                .extract()
                .body().as(BookSeller.class);
        assertNotNull(updatedBookSeller);
        assertEquals("Indie bookstore with Art books by the pound", bookSeller.getDescription());

    }

    @Test
    @Order(4)
    void getBookSellerById() {
        given().get("/{id}", 1L)
                .then()
                .statusCode(200)
                .body("id", notNullValue());
    }

    @Test
    @Order(5)
    void getBookSellerByIdAlt() {
        BookSeller bookSeller = given()
                .when().get("/3")
                .then()
                .statusCode(200)
                .extract()
                .body().as(BookSeller.class);
        assertNotNull(bookSeller);
        assertEquals(3L, bookSeller.getId());
    }

    @Test
    @Order(6)
    void removeBookSellerById() {
        given()
                .contentType("application/json")
                .when()
                .delete("/4")
                .then()
                .statusCode(204);
    }

}
