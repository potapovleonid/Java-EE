package ru.des.home.persist;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Entity
@Table(name = "product_tbl")
@NamedQueries({
        @NamedQuery(name = "findAll", query = "from Product"),
        @NamedQuery(name = "countAll", query = "select count(*) from Product"),
        @NamedQuery(name = "deleteById", query = "delete from Product p where p.id = :id")
})
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_fld")
    private String title;

    @Column(name = "description_fld", length = 1024)
    private String description;

    @Column(name = "price_fld")
    private BigDecimal price;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false)
    private Order order;

    @OneToMany(mappedBy = "product")
    private List<Category> categories;

    public Product() {
    }

    public Product(Long id, String title, String description, BigDecimal price, List<Category> categories) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.categories = categories;
    }

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
