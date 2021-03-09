package ru.des.home.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;

@Stateless
public class ProductRepository {

    private static final Logger logger = LoggerFactory.getLogger(ProductRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    @PostConstruct
    public void init() throws Exception {
        if (countAll() == 0) {
            try {

                saveOrUpdate(new Product(null, "Product  1",
                        "Description of product 1", new BigDecimal(100)));
                saveOrUpdate(new Product(null, "Product  2",
                        "Description of product 2", new BigDecimal(200)));
                saveOrUpdate(new Product(null, "Product  3",
                        "Description of product 3", new BigDecimal(200)));

            } catch (Exception ex) {
                logger.error("", ex);
            }
        }
    }

    public List<Product> findAll() {
        return em.createNamedQuery("findAll", Product.class)
                .getResultList();
    }

    public Product findById(Long id) {
        return em.find(Product.class, id);
    }

    public Long countAll() {
        return em.createNamedQuery("countAll", Long.class)
                .getSingleResult();
    }

    public void saveOrUpdate(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
    }

    public Product saveOrUpdateAndReturn(Product product) {
        if (product.getId() == null) {
            em.persist(product);
        }
        em.merge(product);
        return product;
    }

    public void deleteById(Long id) {
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

}
