package org.acme;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.model.Book;
import org.acme.repository.BookRepository;
import org.acme.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.mockito.Mockito.when;

// Testing with InjectMock
@QuarkusTest
public class BookServiceInjectMockTest {

    @Inject
    BookService bookService;

    @InjectMock
    BookRepository bookRepository;

    @BeforeEach
    void setUp() {
        when(bookRepository.findBy("Lewis"))
                .thenReturn(Arrays.stream(new Book[] {
                        new Book("Out of the Silent Planet","C.S. Lewis"),
                        new Book("Perelandra", "C.S. Lewis"),
                        new Book("That Hideous Strength", "C.S. Lewis")
                }));
    }

    @Test
    void findBookByAuthorTest() {
        Assertions.assertEquals(3, bookService.find("Lewis").size());
    }
}
