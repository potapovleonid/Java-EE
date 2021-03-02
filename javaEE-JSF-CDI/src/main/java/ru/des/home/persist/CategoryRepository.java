package ru.des.home.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Named
@ApplicationScoped
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;

    @Resource
    private UserTransaction ut;

    public List<Category> findAll(){
        return em.createNamedQuery("findAll", Category.class)
                .getResultList();
    }

    public Category findById(Long id){
        return em.find(Category.class, id);
    }

    @Transactional
    public void saveOrUpdate(Category category){
        if (category.getId() == null){
            em.persist(category);
        }
        em.merge(category);
    }

    @Transactional
    public void deleteById(Long id){
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
