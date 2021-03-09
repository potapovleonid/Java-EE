package ru.des.home.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import javax.transaction.UserTransaction;
import java.util.List;

@Stateless
public class CategoryRepository {

    private static final Logger logger = LoggerFactory.getLogger(CategoryRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    public List<Category> findAll(){
        return em.createNamedQuery("findAll", Category.class)
                .getResultList();
    }

    public Category findById(Long id){
        return em.find(Category.class, id);
    }

    public Category getReference(Long id) {
        return em.getReference(Category.class, id);
    }

    public void saveOrUpdate(Category category){
        if (category.getId() == null){
            em.persist(category);
        }
        em.merge(category);
    }

    public void deleteById(Long id){
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }
}
