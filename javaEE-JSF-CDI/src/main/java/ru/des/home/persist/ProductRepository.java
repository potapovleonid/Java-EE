package ru.des.home.persist;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Named
@ApplicationScoped
public class ProductRepository {

    private final Map<Long, Product> products = new ConcurrentHashMap<>();

    private final AtomicLong id = new AtomicLong(0);

    @PostConstruct
    public void init(){
        this.saveOrUpdate(new Product(null, "Product  1", "Butter", new BigDecimal(700)));
        this.saveOrUpdate(new Product(null, "Product  2", "Meat", new BigDecimal(1500)));
        this.saveOrUpdate(new Product(null, "Product  3", "Bread", new BigDecimal(100)));
        this.saveOrUpdate(new Product(null, "Product  4", "Apple", new BigDecimal(50)));
    }

    public List<Product> findAll(){
        return new ArrayList<>(products.values());
    }

    public Product findById(Long id){
        return products.get(id);
    }

    public void saveOrUpdate(Product product){
        if (product.getId() == null){
            Long id = this.id.incrementAndGet();
            product.setId(id);
        }

        products.put(product.getId(), product);
    }

    public void deleteById(Long id){
        products.remove(id);
    }

}
