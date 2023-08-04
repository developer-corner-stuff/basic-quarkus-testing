package org.acme;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.common.http.TestHTTPResource;
import io.quarkus.test.junit.QuarkusIntegrationTest;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.Charset;

import static org.junit.jupiter.api.Assertions.assertTrue;

@QuarkusIntegrationTest
public class BookStoreResourceIT extends BookStoreResourceTest {
}
