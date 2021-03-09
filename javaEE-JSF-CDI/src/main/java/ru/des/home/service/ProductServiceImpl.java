package ru.des.home.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.des.home.persist.Category;
import ru.des.home.persist.CategoryRepository;
import ru.des.home.persist.Product;
import ru.des.home.persist.ProductRepository;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import java.util.List;
import java.util.stream.Collectors;

@Stateless
public class ProductServiceImpl implements ProductService{

    private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

    @EJB
    private ProductRepository productRepository;

    @EJB
    private CategoryRepository categoryRepository;

    @Override
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if(product != null){
            return new ProductRepr(product);
        }
        return null;
    }

    @Override
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    public void saveOrUpdate(ProductRepr product) {
        logger.info("Saving product with id {}" , product.getId());

        Category category = null;

        if (product.getCategoryID() != null) {
            category = categoryRepository.getReference(product.getCategoryID());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @Override
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }
}
