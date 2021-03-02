package ru.des.home.controllers;

import ru.des.home.persist.Product;
import ru.des.home.persist.ProductRepository;

import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;

@Named
@SessionScoped
public class ProductController implements Serializable {

    @Inject
    private ProductRepository repository;

    private Product product;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public String createProduct(){
        this.product = new Product();
        return "/product_form.xhtml?faces-redirect-true";
    }

    public List<Product> getAllProducts(){
        return repository.findAll();
    }

    public String editProduct(Product product){
        this.product = product;
        return "/product_form.xhtml?faces-redirect-true";
    }

    public void deleteProduct(Product product){
        repository.deleteById(product.getId());
    }

    public String saveProduct(){
        repository.saveOrUpdate(product);
        return "/product.xhtml?faces-redirect-true";
    }

}
