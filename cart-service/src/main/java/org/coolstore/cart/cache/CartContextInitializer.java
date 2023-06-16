package org.coolstore.cart.cache;


import org.coolstore.cart.model.Product;
import org.coolstore.cart.model.Promotion;
import org.coolstore.cart.model.ShoppingCart;
import org.coolstore.cart.model.ShoppingCartItem;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder (includeClasses = {ShoppingCart.class, ShoppingCartItem.class, Promotion.class, Product.class }, schemaPackageName = "coolstore")
interface CartContextInitializer extends SerializationContextInitializer {

}
