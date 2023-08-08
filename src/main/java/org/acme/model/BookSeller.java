package org.acme.model;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import jakarta.persistence.*;

@Entity
@Table(name="bookseller")
@NamedQuery(name="BookSeller.findAll", query="select b from BookSeller b order by b.name", hints=@QueryHint(name="org.hibernate.cacheable", value="true"))
@Cacheable
public class BookSeller extends PanacheEntityBase {

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "bookseller_id_seq")
    @SequenceGenerator(name = "bookseller_id_seq", sequenceName = "bookseller_sequence", initialValue = 1, allocationSize = 1)
    private Long id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String name;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    private String description;
}
