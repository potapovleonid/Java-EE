package ru.des.home.service;

import ru.des.home.persist.Product;

import javax.ejb.Local;
import java.util.List;

@Local
public interface ProductService {

    List<ProductRepr> findAll();

    ProductRepr findById(Long id);

    Product findByTitle(String title);

    List<Product> findAllByCategoryId(Long id);

    Long countAll();

    void saveOrUpdate(ProductRepr product);

    void deleteById(Long id);

}
