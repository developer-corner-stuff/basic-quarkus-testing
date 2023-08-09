package org.acme;

import io.quarkus.test.junit.QuarkusTest;
import jakarta.inject.Inject;
import jakarta.transaction.Status;
import jakarta.transaction.Transactional;
import org.acme.repository.BookRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

// Test will fail without a transaction
// Add QuarkusTransactionalTest interface here
// @QuarkusTest
@TransactionalQuarkusTest
public class BookRespositoryInjectionTest {

    @Inject
    BookRepository bookRepository;

    @Test
    void findByAuthorTest() {
        Assertions.assertTrue(bookRepository.findBy("Cixin Liu").findAny().isPresent());
    }

    @Test
    void bookRepositoryTransactionTest() throws Exception {
        Assertions.assertEquals(true, bookRepository.getEntityManager().isJoinedToTransaction());
    }
}
