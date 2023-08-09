package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Tests injection into a test
@QuarkusTest
public class BookServiceInjectionTest {

    @Inject
    BookService bookService;

    @Test
    public void findByIdTest() {
        Assertions.assertEquals("Isaac Asimov", bookService.findById(1L).getAuthor());
    }
}
