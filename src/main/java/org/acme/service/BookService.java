package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;
import org.acme.model.Book;
import org.acme.repository.BookRepository;

import jakarta.inject.Inject;

import java.security.InvalidParameterException;
import java.util.Optional;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Transactional
@ApplicationScoped
public class BookService {

    @Inject
    BookRepository bookRepository;

    public Set<Book> find(String query) {
        if (query == null) {
            return bookRepository.findAll().stream().collect(toSet());
        }
        return bookRepository.findBy(query).collect(toSet());
    }

    public Book findById(Long id) {
        return bookRepository.findById(id);
    }

    public void create(Book book) {
        bookRepository.persist(book);
    }

    public Book update(Long bookId, Book book) {
        book.setId(bookId);
        return bookRepository.update(book).orElseThrow(() -> new InvalidParameterException("Book not found"));
    }

    public boolean remove(Long bookId) {
        return bookRepository.deleteById(bookId);
    }
}
