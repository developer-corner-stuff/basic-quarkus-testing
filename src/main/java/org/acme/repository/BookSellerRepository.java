package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;
import org.acme.model.BookSeller;

import jakarta.annotation.PostConstruct;
import java.util.Optional;
import java.util.stream.Stream;

import static io.quarkus.panache.common.Parameters.with;

@ApplicationScoped
public class BookSellerRepository implements PanacheRepository<BookSeller> {

    public Stream<BookSeller> findBy(String query) {
        return find("name like :query or name like :query", with("query", "%"+query+"%")).stream();
    }

    public Optional<BookSeller> update(BookSeller bookSeller) {
        var updateBookSeller = this.findByIdOptional(bookSeller.getId());
        if (updateBookSeller.isEmpty()){
            return Optional.empty();
        }

        var savedBookSeller = updateBookSeller.get();
        savedBookSeller.setName(bookSeller.getName());
        savedBookSeller.setDescription(bookSeller.getDescription());

        return Optional.of(savedBookSeller);
    }

}
