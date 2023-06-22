package org.coolstore.cart.resource;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import org.coolstore.cart.model.Cart;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import org.coolstore.cart.resource.model.CartItem;
import org.coolstore.cart.service.CartService;
import org.eclipse.microprofile.openapi.annotations.Operation;

import static jakarta.ws.rs.core.MediaType.APPLICATION_JSON;

@Path("/api/cart/v2")
public class CartResourceV2 {

    @Inject
    CartService shoppingCartService;

    @GET
    @Path("/{cartId}")
    public Cart getCart(@PathParam("cartId") String  cartId) {
        return shoppingCartService.getShoppingCart(cartId);
    }

    @PUT
    @Path("/{cartId}")
    @Operation(summary = "Add item to cart")
    @Consumes(APPLICATION_JSON)
    public Cart addItem(@PathParam("cartId") String  cartId, CartItem item) throws Exception {
        return shoppingCartService.addItem(cartId, item.itemId, item.quantity);
    }

    @DELETE
    @Path("/{cartId}")
    @Operation(summary = "remove item to cart")
    @Consumes(APPLICATION_JSON)
    public Cart removeItem(@PathParam("cartId") String  cartId, CartItem item) throws Exception {
        return shoppingCartService.deleteItem(cartId, item.itemId, item.quantity);
    }

    @POST
    @Path("/{cartId}")
    public Cart checkout(@PathParam("cartId") String  cartId) {
        return shoppingCartService.checkout(cartId);
    }

}
