package ru.des.home.persist;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.des.home.service.ProductRepr;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class CartRepository {

    private static final Logger logger = LoggerFactory.getLogger(CartRepository.class);

    @PersistenceContext(unitName = "ds")
    private EntityManager em;


}
