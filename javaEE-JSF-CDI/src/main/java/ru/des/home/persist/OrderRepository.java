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
public class OrderRepository {

    private static final Logger logger = LoggerFactory.getLogger(Order.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


    public List<Order> findAll(){
        return em.createNamedQuery("findAll", Order.class)
                .getResultList();
    }

    public Category findById(Long id){
        return em.find(Category.class, id);
    }

    public void saveOrUpdate(Order order){
        if (order.getId() == null){
            em.persist(order);
        }
        em.merge(order);
    }

    public void deleteById(Long id){
        em.createNamedQuery("deleteById")
                .setParameter("id", id)
                .executeUpdate();
    }

}
