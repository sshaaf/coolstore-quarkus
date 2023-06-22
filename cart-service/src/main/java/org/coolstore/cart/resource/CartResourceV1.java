package org.coolstore.cart.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.coolstore.cart.model.Cart;
import org.coolstore.cart.model.Order;


import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

// import org.apache.kafka.clients.producer.KafkaProducer;
// import org.apache.kafka.clients.producer.Producer;
// import org.apache.kafka.clients.producer.ProducerRecord;
// import org.apache.kafka.common.header.internals.RecordHeaders;

import org.coolstore.cart.service.CartService;

import io.quarkus.runtime.StartupEvent;


@Path("/api/cart/v1")
public class CartResourceV1 {

    // TODO: Change the entire resource. Methods conventions are broken

    // TODO: Add annotation of orders messaging configuration here

    @Inject
    CartService shoppingCartService;

    @GET
    @Path("{cartId}")
    public Cart getCart(String cartId) {
        return shoppingCartService.getShoppingCart(cartId);
    }

    @GET
    @Path("{cartId}/{itemId}/{quantity}")
    public Cart add(String cartId, String itemId, int quantity) throws Exception {
        return shoppingCartService.addItem(cartId, itemId, quantity);
    }

    @GET
    @Path("{cartId}/{tmpId}")
    public Cart set(String cartId, String tmpId) throws Exception {
        return shoppingCartService.set(cartId, tmpId);
    }

    @DELETE
    @Path("{cartId}/{itemId}/{quantity}")
    public Cart delete(String cartId, String itemId, int quantity) throws Exception {
        return shoppingCartService.deleteItem(cartId, itemId, quantity);
    }

    @GET
    @Path("/checkout/{cartId}")
    public Cart checkout(String cartId, Order order) {
        // TODO ADD for KAFKA
        //sendOrder(order, cartId);
        return shoppingCartService.checkout(cartId);
    }

    // TODO ADD for KAFKA or AMQ
    private void sendOrder(Order order, String cartId) {

    }

    // TODO ADD for KAFKA
    public void init(@Observes StartupEvent ev) {

    }

}
