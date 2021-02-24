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
            product = repository.saveOrUpdateAndReturn(product);
        }
        products.put(product.getId(), product);
    }

    public void deleteProductFromCart(Product product) throws Exception {
        if (!products.remove(product.getId(), product)){
            throw new Exception("There is no such product in the cart");
        }
    }

}
