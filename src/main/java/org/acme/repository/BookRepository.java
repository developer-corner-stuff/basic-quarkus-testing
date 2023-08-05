package org.acme.repository;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import jakarta.enterprise.context.ApplicationScoped;
import org.acme.model.Book;

import javax.annotation.PostConstruct;
import java.util.Optional;
import java.util.stream.Stream;

import static io.quarkus.panache.common.Parameters.with;

@ApplicationScoped
public class BookRepository implements PanacheRepository<Book> {

    public Stream<Book> findBy(String query) {
        return find("author like :query or title like :query", with("query", "%"+query+"%")).stream();
    }

    public Optional<Book> update(Book book) {
        var updateBook = this.findByIdOptional(book.getId());
        if (updateBook.isEmpty()){
            return Optional.empty();
        }

        var savedBook = updateBook.get();
        savedBook.setTitle(book.getTitle());
        savedBook.setAuthor(book.getAuthor());

        return Optional.of(savedBook);
    }

}
