package org.acme;

import io.quarkus.test.junit.QuarkusMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import org.acme.model.Book;
import org.acme.repository.BookRepository;
import org.acme.service.BookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;

// Mock within the scope of a test
@QuarkusTest
public class BookServiceMockTest {

    @Inject
    BookService bookService;

    @BeforeEach
    void setUp() {
        BookRepository mock = Mockito.mock(BookRepositoryTest.class);
        Mockito.when(mock.findBy("Andrzej"))
                .thenReturn(Arrays.stream(new Book[] {
                        new Book("Sword of Destiny", "Andrzej Sapkowski"),
                        new Book( "The Last Wish", "Andrzej Sapkowski")
                }));
        QuarkusMock.installMockForType(mock, BookRepository.class);
    }

    @Test
    void findBookByAuthorTest() {
        Assertions.assertEquals(2, bookService.find("Andrzej").size());
    }
}
