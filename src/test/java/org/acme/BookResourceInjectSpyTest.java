package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.mockito.InjectSpy;
import io.restassured.http.ContentType;
import org.acme.service.BookService;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.mockito.Mockito.verify;

// Only spying and not replacing
@QuarkusTest
public class BookResourceInjectSpyTest {

    @InjectSpy
    BookService bookService;

    @Test
    void getBookByAuthorTest() {
        given().contentType(ContentType.JSON).param("query", "Cixin")
                .when().get("/book")
                .then().statusCode(200);

        verify(bookService).find("Cixin");

    }
}
