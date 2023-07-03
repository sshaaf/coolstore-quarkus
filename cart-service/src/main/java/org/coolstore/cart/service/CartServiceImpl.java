package org.coolstore.cart.service;

import io.quarkus.infinispan.client.Remote;
import org.coolstore.cart.model.Cart;
import org.coolstore.cart.model.CartItem;
import org.coolstore.cart.model.Product;
import org.infinispan.client.hotrod.RemoteCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;


@ApplicationScoped
public class CartServiceImpl implements CartService {
    public static final String CART_CACHE = "carts";
    private static final Logger log = LoggerFactory.getLogger(CartServiceImpl.class);

    // primary
    @Inject
    @Remote(CART_CACHE)
    RemoteCache<String, Cart> carts;

    @Inject
    PromotionService ps;

    @Inject
    ShippingService ss;

    private Map<String, Product> productMap = new HashMap<>();

    @Override
    public Cart getShoppingCart(String cartId) {
        if (!carts.containsKey(cartId)) {
            Cart cart = new Cart(cartId);
            carts.put(cartId, cart);
            return cart;
        }

        Cart cart = carts.get(cartId);
        priceShoppingCart(cart);
        carts.put(cartId, cart);
        return cart;
    }

    public void priceShoppingCart(Cart sc) {
        if (sc != null) {
            initShoppingCartForPricing(sc);

            if (sc.getCartItemList() != null && sc.getCartItemList().size() > 0) {
                ps.applyCartItemPromotions(sc);

                for (CartItem sci : sc.getCartItemList()) {
                    //sc.setCartItemPromoSavings(sc.getCartItemPromoSavings() + sci.getPromoSavings() * sci.getQuantity());
                    sc.setCartItemTotal(sc.getCartItemTotal() + sci.getPrice() * sci.getQuantity());
                }

                ss.calculateShipping(sc);
            }

            ps.applyShippingPromotions(sc);
            sc.setCartTotal(sc.getCartItemTotal() + sc.getShippingTotal());
        }
    }

    void initShoppingCartForPricing(Cart sc) {
        sc.setCartItemTotal(0);
        sc.setCartItemPromoSavings(0);
        sc.setShippingTotal(0);
        sc.setShippingPromoSavings(0);
        sc.setCartTotal(0);

        for (CartItem sci : sc.getCartItemList()) {
            Product p = getProduct(sci.getProduct().getItemId());

            //if product exist, create new product to reset price
            if (p != null) {
                sci.setProduct(new Product(p.getItemId(), p.getName(), p.getDesc(), p.getPrice()));
                sci.setPrice(p.getPrice());
            }
        }
    }

    @Override
    public Product getProduct(String itemId) {
        if (!productMap.containsKey(itemId)) {
            // Fetch and cache products. TODO: Cache should expire at some point!
            productMap = getProducts().stream().collect(Collectors.toMap(Product::getItemId, Function.identity()));
        }

        return productMap.get(itemId);
    }

    private List<Product> getProducts() {
        // Mock Method for catalog service
        List<Product> products = new ArrayList<>();
        products.add(new Product("329299", "Quarkus T-shirt", "", 10.00));
        products.add(new Product("329199", "Pronounced Kubernetes", "", 9.00));
        products.add(new Product("165613", "Knit socks", "", 4.15));
        products.add(new Product("165614", "Quarkus H2Go water bottle", "", 14.45));
        products.add(new Product("165954", "Patagonia Refugio pack 28L", "", 6.00));
        products.add(new Product("444434", "Red Hat Impact T-shirt", "", 9.00));
        products.add(new Product("444435", "Quarkus twill cap", "", 13.00));
        products.add(new Product("444437", "Lytro Camera", "Nanobloc Universal Webcam Cover", 2.75));

        return products;
    }

    @Override
    public Cart deleteItem(String cartId, String itemId, int quantity) {
        List<CartItem> toRemoveList = new ArrayList<>();

        Cart cart = getShoppingCart(cartId);

        cart.getCartItemList().stream()
                .filter(sci -> sci.getProduct().getItemId().equals(itemId))
                .forEach(sci -> {
                    if (quantity >= sci.getQuantity()) {
                        toRemoveList.add(sci);
                    } else {
                        sci.setQuantity(sci.getQuantity() - quantity);
                    }
                });

        toRemoveList.forEach(cart::removeCartItem);
        priceShoppingCart(cart);
        carts.put(cartId, cart);

        return cart;
    }

    @Override
    public Cart checkout(String cartId) {
        Cart cart = getShoppingCart(cartId);
        cart.resetCartItemList();
        priceShoppingCart(cart);
        carts.put(cartId, cart);
        return cart;
    }

    @Override
    public Cart addItem(String cartId, String itemId, int quantity) {
        Cart cart = getShoppingCart(cartId);
        Product product = getProduct(itemId);

        if (product == null) {
            log.warn("Invalid product {} request to get added to the shopping cart. No product added", itemId);
            return cart;
        }


        CartItem sci = new CartItem(product, product.getPrice(), quantity, 0.0f);
        cart.addCartItem(sci);

        try {
            priceShoppingCart(cart);
            cart.setCartItemList(dedupeCartItems(cart));
        } catch (Exception ex) {
            cart.removeCartItem(sci);
            throw ex;
        }

        carts.put(cartId, cart);
        return cart;
    }

    @Override
    public Cart set(String cartId, String tmpId) {

        Cart cart = getShoppingCart(cartId);
        Cart tmpCart = getShoppingCart(tmpId);

        if (tmpCart != null) {
            cart.resetCartItemList();
            cart.setCartItemList(tmpCart.getCartItemList());
        }

        try {
            priceShoppingCart(cart);
            cart.setCartItemList(dedupeCartItems(cart));
        } catch (Exception ex) {
            throw ex;
        }

        carts.put(cartId, cart);
        return cart;
    }

    List<CartItem> dedupeCartItems(Cart sc) {
        List<CartItem> result = new ArrayList<>();
        Map<String, Integer> quantityMap = new HashMap<>();
        for (CartItem sci : sc.getCartItemList()) {
            if (quantityMap.containsKey(sci.getProduct().getItemId())) {
                quantityMap.put(sci.getProduct().getItemId(), quantityMap.get(sci.getProduct().getItemId()) + sci.getQuantity());
            } else {
                quantityMap.put(sci.getProduct().getItemId(), sci.getQuantity());
            }
        }

        for (String itemId : quantityMap.keySet()) {
            Product p = getProduct(itemId);
            CartItem newItem = new CartItem();
            newItem.setQuantity(quantityMap.get(itemId));
            newItem.setPrice(p.getPrice());
            newItem.setProduct(p);
            result.add(newItem);
        }

        return result;
    }

}
