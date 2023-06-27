package org.coolstore.cart.service;

import io.quarkus.runtime.StartupEvent;
import org.infinispan.client.hotrod.RemoteCache;
import org.infinispan.client.hotrod.RemoteCacheManager;
import org.infinispan.commons.configuration.XMLStringConfiguration;

import jakarta.enterprise.event.Observes;
import jakarta.inject.Inject;

public class CacheService {

/*    public static final String CART_CACHE = "cart";
    public static final String PRODUCT_CACHE = "cart";

    @Inject
    RemoteCacheManager cacheManager;

    private static final String CACHE_CONFIG_OLD = "<infinispan><cache-container>" +
            "<serialization marshaller=\"org.infinispan.commons.marshall.JavaSerializationMarshaller\">\n" +
            "</serialization>"+
            "<distributed-cache name=\"%s\"></distributed-cache>" +
            "</cache-container></infinispan>";

    private static final String CACHE_CONFIG_CART = "{\n" +
            "  \"carts\": {\n" +
            "    \"distributed-cache\": {\n" +
            "      \"owners\": \"2\",\n" +
            "      \"mode\": \"SYNC\",\n" +
            "      \"statistics\": true,\n" +
            "      \"encoding\": {\n" +
            "        \"media-type\": \"application/x-protostream\"\n" +
            "      },\n" +
            "      \"locking\": {\n" +
            "        \"isolation\": \"REPEATABLE_READ\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    private static final String CACHE_CONFIG_PRODUCTS = "{\n" +
            "  \"products\": {\n" +
            "    \"distributed-cache\": {\n" +
            "      \"owners\": \"2\",\n" +
            "      \"mode\": \"SYNC\",\n" +
            "      \"statistics\": true,\n" +
            "      \"encoding\": {\n" +
            "        \"media-type\": \"application/x-protostream\"\n" +
            "      },\n" +
            "      \"locking\": {\n" +
            "        \"isolation\": \"REPEATABLE_READ\"\n" +
            "      }\n" +
            "    }\n" +
            "  }\n" +
            "}";

    void onStart(@Observes StartupEvent ev) {
        RemoteCache<Object, Object> cache = cacheManager.administration().getOrCreateCache(CART_CACHE,
                new XMLStringConfiguration(String.format(CACHE_CONFIG_CART, CART_CACHE)));
    }*/


}