package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.junit.TestProfile;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.Matchers.hasItems;

@QuarkusTest
@TestProfile(CustomBookProfileTest.class)
public class CustomBookResourceTest {

    public static final String NEW_BOOK_ENDPOINT = "/custom/book";

    @Test
    void getAllBooks() {
        given().contentType(ContentType.JSON)
                .when().get(NEW_BOOK_ENDPOINT)
                .then().statusCode(200)
                .body("size()", is(2))
                .body("author", hasItems("Alfred Aloysius Horn","Cixin Liu"));

    }
}
