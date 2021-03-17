package ru.des.home.service;

import ru.des.home.persist.Category;
import ru.des.home.persist.Product;

import java.math.BigDecimal;

public class ProductRepr {

    private Long id;

    private String title;

    private String description;

    private BigDecimal price;

    private Long categoryID;

    private String category;

    public ProductRepr() {
    }

    public ProductRepr(Product product) {
        this.id = product.getId();
        this.title = product.getTitle();
        this.description = product.getDescription();
        this.price = product.getPrice();

        Category cat = product.getCategory();
        if (cat != null) {
            categoryID = cat.getId();
            category = cat.getTitle();
        } else {
            category = null;
            categoryID = null;
        }
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

    public Long getCategoryID() {
        return categoryID;
    }

    public void setCategoryID(Long categoryID) {
        this.categoryID = categoryID;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
