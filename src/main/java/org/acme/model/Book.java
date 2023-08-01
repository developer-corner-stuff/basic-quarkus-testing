package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name="book")
@NamedQuery(name="Books.findAll", query="select b from Book b order by b.author", hints=@QueryHint(name="org.hibernate.cacheable", value="true"))
@Cacheable
public class Book extends PanacheEntity {
    private String title;
    private String author;

    public Book() {

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
