package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import org.acme.model.BookSeller;
import org.acme.repository.BookSellerRepository;

import java.security.InvalidParameterException;
import java.util.Set;

import static java.util.stream.Collectors.toSet;

@Transactional
@ApplicationScoped
public class BookSellerService {

    @Inject
    BookSellerRepository bookSellerRepository;

    public Set<BookSeller> find(String query) {
        if (query == null) {
            return bookSellerRepository.findAll().stream().collect(toSet());
        }
        return bookSellerRepository.findBy(query).collect(toSet());
    }

    public BookSeller findById(Long id) {
        return bookSellerRepository.findById(id);
    }

    public void create(BookSeller bookSeller) {
        bookSeller.persist(bookSeller);
    }

    public BookSeller update(Long bookSellerId, BookSeller bookSeller) {
        bookSeller.setId(bookSellerId);
        return bookSellerRepository.update(bookSeller).orElseThrow(() -> new InvalidParameterException("BookSeller not found"));
    }

    public boolean remove(Long bookSellerId) {
        return bookSellerRepository.deleteById(bookSellerId);
    }
}
