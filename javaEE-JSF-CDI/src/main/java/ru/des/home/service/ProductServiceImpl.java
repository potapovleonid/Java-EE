package ru.des.home.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.des.home.persist.Category;
import ru.des.home.persist.CategoryRepository;
import ru.des.home.persist.Product;
import ru.des.home.persist.ProductRepository;

import javax.annotation.security.RolesAllowed;
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
    @RolesAllowed({"USER", "ADMIN"})
    public List<ProductRepr> findAll() {
        return productRepository.findAll().stream()
                .map(ProductRepr::new)
                .collect(Collectors.toList());
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public ProductRepr findById(Long id) {
        Product product = productRepository.findById(id);
        if(product != null){
            return new ProductRepr(product);
        }
        return null;
    }

    @Override
    @RolesAllowed("ADMIN")
    public Long countAll() {
        return productRepository.countAll();
    }

    @Override
    @RolesAllowed("ADMIN")
    public void saveOrUpdate(ProductRepr product) {
        logger.info("Saving product with id {}" , product.getId());

        Category category = null;

        if (product.getCategoryID() != null) {
            category = categoryRepository.getReference(product.getCategoryID());
        }
        productRepository.saveOrUpdate(new Product(product, category));
    }

    @Override
    @RolesAllowed("ADMIN")
    public void deleteById(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public Product findByTitle(String title) {
        return productRepository.findByTitle(title);
    }

    @Override
    @RolesAllowed({"USER", "ADMIN"})
    public List<Product> findAllByCategoryId(Long id) {
        return productRepository.findProductByCategoryId(id);
    }
}
