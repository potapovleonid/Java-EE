package ru.des.home.controllers;

import ru.des.home.persist.Cart;
import ru.des.home.persist.Product;
import ru.des.home.service.CartService;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Named
@SessionScoped
public class CartController implements Serializable {

    @EJB
    private CartService cartService;


    // TODO
    private Map<Long, Product> productMap = new HashMap<>();

    public List<Product> getProductToCart(){
        return new ArrayList<>(productMap.values());
    }

    public String addToCart(Product product) {
        Cart cart = new Cart();
        if (product == null) {
            throw new IllegalArgumentException("add product to cart is null");
        }

        if (cart == null) {
            cart = new Cart();
        }

        cart.addProductToCart(product);
        return "/cart.xhtml?faces-redirect-true";
    }

    public String removeFromCart(Product product) throws Exception {
        Cart cart = new Cart();
        if (product.getId() == null){
            throw new IllegalArgumentException("remove product from cart ID is null");
        }
        if (cart == null){
            throw new Exception("Cart not created");
        }
        cart.deleteProductFromCart(product);
        return "/cart.xhtml?faces-redirect-true";
    }


}
