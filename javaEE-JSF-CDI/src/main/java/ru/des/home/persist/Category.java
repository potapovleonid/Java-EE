package ru.des.home.persist;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "category_tbl")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "from Category"),
        @NamedQuery(name = "countAll", query = "select count(*) from Category"),
        @NamedQuery(name = "deleteById", query = "delete from Category p where p.id = :id")
})
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "category_id")
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @OneToMany(mappedBy = "category")
    private List<Product> products;

    public Category() {
    }

    public Category(Long id, String title) {
        this.id = id;
        this.title = title;
    }

    public Category(String title) {
        this.title = title;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
