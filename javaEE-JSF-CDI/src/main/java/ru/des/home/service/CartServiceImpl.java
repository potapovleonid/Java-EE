package ru.des.home.service;

import javax.ejb.Stateful;
import java.util.HashMap;
import java.util.Map;

@Stateful
public class CartServiceImpl implements CartService{

    private final Map<Long, ProductRepr> products = new HashMap<>();

    @Override
    public void addToCart(ProductRepr product) {

    }

    @Override
    public void deleteProductFromCart(ProductRepr productRepr) {

    }
}
