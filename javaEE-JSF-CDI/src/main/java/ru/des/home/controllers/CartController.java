package ru.des.home.controllers;

import ru.des.home.persist.Product;

import java.util.HashMap;
import java.util.Map;

public class CartController {

    private Map<Long, Product> productMap = new HashMap<>();

    public String addToCart(Product product) {
        return "";
    }

    public String removeFromCart(Product product) {
        return "";
    }

}
