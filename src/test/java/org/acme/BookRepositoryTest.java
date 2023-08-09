package org.acme;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Alternative;
import org.acme.model.Book;
import org.acme.repository.BookRepository;

import javax.annotation.PostConstruct;
import javax.annotation.Priority;

// Testing using CDI

@Priority(1)
@Alternative
@ApplicationScoped
public class BookRepositoryTest extends BookRepository {

    @PostConstruct
    public void init () {
        persist(new Book("Blood of Elves", "Andrzej Sapkowski"),
                new Book("Time of Contempt", "Andrzej Sapkowski"),
                new Book("Baptism of Fire", "Andrzej Sapkowski"));
    }
}
