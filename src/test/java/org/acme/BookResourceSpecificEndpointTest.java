package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusTest;
import org.acme.resource.BookResource;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusTest
public class BookResourceSpecificEndpointTest {

    @TestHTTPEndpoint(BookResource.class)
    @TestHTTPResource("")
    URL bookResourceEndpoint;

    @Test
    void getBooksAuthorShouldBeFound() throws IOException {
        assertTrue(IOUtils.toString(bookResourceEndpoint.openStream(), Charset.defaultCharset()).contains("Isaac Asimov"));
    }
}
