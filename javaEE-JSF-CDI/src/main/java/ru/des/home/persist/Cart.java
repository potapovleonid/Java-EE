package ru.des.home.persist;

import javax.inject.Inject;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class Cart {

    private Map<Long, Product> products = new ConcurrentHashMap<>();

    @Inject
    private ProductRepository repository;

    public void addProductToCart(Product product){
        if (product.getId() == null) {
            repository.saveOrUpdate(product);
        }
        products.put(product.getId(), product);

    }

}
