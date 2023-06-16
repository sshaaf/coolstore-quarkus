package org.coolstore.cart.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import org.coolstore.cart.model.Order;
import org.coolstore.cart.model.ShoppingCart;



import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;

// import org.apache.kafka.clients.producer.KafkaProducer;
// import org.apache.kafka.clients.producer.Producer;
// import org.apache.kafka.clients.producer.ProducerRecord;
// import org.apache.kafka.common.header.internals.RecordHeaders;

import org.coolstore.cart.service.ShoppingCartService;
import org.eclipse.microprofile.config.inject.ConfigProperty;

import io.quarkus.runtime.StartupEvent;

import java.util.Properties;


@Path("/api/cart")
public class CartResource {

    // TODO: Add annotation of orders messaging configuration here

    @Inject
    ShoppingCartService shoppingCartService;

    @GET
    @Path("{cartId}")
    public ShoppingCart getCart(String cartId) {
        return shoppingCartService.getShoppingCart(cartId);
    }

    @POST
    @Path("{cartId}/{itemId}/{quantity}")
    public ShoppingCart add(String cartId, String itemId, int quantity) throws Exception {
        return shoppingCartService.addItem(cartId, itemId, quantity);
    }

    @POST
    @Path("{cartId}/{tmpId}")
    public ShoppingCart set(String cartId, String tmpId) throws Exception {
        return shoppingCartService.set(cartId, tmpId);
    }

    @DELETE
    @Path("{cartId}/{itemId}/{quantity}")
    public ShoppingCart delete(String cartId, String itemId, int quantity) throws Exception {
        return shoppingCartService.deleteItem(cartId, itemId, quantity);
    }

    @POST
    @Path("/checkout/{cartId}")
    public ShoppingCart checkout(String cartId, Order order) {
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
