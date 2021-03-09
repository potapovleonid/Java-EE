package ru.des.home.service;

import ru.des.home.persist.Cart;

public interface CartService {

    //TODO
    void addToCart(ProductRepr product);
    void deleteProductFromCart(ProductRepr productRepr);

}
