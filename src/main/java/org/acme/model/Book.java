package org.acme.model;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import io.quarkus.hibernate.orm.panache.PanacheEntity;
import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name="book")
@NamedQuery(name="Books.findAll", query="select b from Book b order by b.author", hints=@QueryHint(name="org.hibernate.cacheable", value="true"))
@Cacheable
public class Book extends PanacheEntityBase {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "book_id_seq")
    @SequenceGenerator(name = "book_id_seq", sequenceName = "book_sequence", initialValue = 1, allocationSize = 1)
    private Long id;


    private String title;
    private String author;

    public Book() {

    }

    public Book(String title, String author) {
        this.author = author;
        this.title = title;
    }

    public String getTitle() {
        return this.title;
    }

    public String getAuthor() {
        return this.author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setAuthor(String author){
        this.author = author;
    }
}
