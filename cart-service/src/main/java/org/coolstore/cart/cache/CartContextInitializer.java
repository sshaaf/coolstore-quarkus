package org.coolstore.cart.cache;


import org.coolstore.cart.model.Product;
import org.coolstore.cart.model.Promotion;
import org.coolstore.cart.model.Cart;
import org.coolstore.cart.model.CartItem;
import org.infinispan.protostream.SerializationContextInitializer;
import org.infinispan.protostream.annotations.AutoProtoSchemaBuilder;

@AutoProtoSchemaBuilder (includeClasses = {Cart.class, CartItem.class, Promotion.class, Product.class }, schemaPackageName = "coolstore")
interface CartContextInitializer extends SerializationContextInitializer {

}
