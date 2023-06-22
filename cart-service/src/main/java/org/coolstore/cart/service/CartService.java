package org.coolstore.cart.service;

import org.coolstore.cart.model.Cart;
import org.coolstore.cart.model.Product;

public interface CartService {

    public Cart getShoppingCart(String cartId);
    public Product getProduct(String itemId);
    public Cart deleteItem(String cartId, String itemId, int quantity);
    public Cart checkout(String cartId);
    public Cart addItem(String cartId, String itemId, int quantity);
    public Cart set(String cartId, String tmpId);
    public void priceShoppingCart(Cart sc);

}
