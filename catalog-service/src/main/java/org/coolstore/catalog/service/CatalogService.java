package org.coolstore.catalog.service;

import org.coolstore.catalog.ProductMapper;
import org.coolstore.catalog.entity.Catalog;
import org.coolstore.catalog.model.Inventory;
import org.coolstore.catalog.model.Product;
import org.coolstore.catalog.repository.CatalogRepository;
import org.coolstore.catalog.resource.client.InventoryService;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;


@ApplicationScoped
public class CatalogService {

    @Inject
    CatalogRepository repository;

    @RestClient
    InventoryService inventoryService;

    public List<Product> getAllProductsWithQuantity(){
        List<Product> products = ProductMapper.INSTANCE.fromCatalog(repository.listAll());
        products.forEach(p -> p.setQuantity(inventoryService.getByItemId(p.getItemId()).quantity));
        return products;
    }


    public Product findById(String itemId) {
        Catalog catalog = repository.findById(itemId);
        if(catalog != null) {
            Product product = ProductMapper.INSTANCE.fromCatalog(catalog);
            Inventory inventory = inventoryService.getByItemId(catalog.itemId);
            product.setQuantity(inventory.quantity);
            return product;
        }
        else
            return null;
    }

    public Product create(Product product) {
        return null;
    }

    public Product update(String itemId) {
        return null;
    }

    public Product delete(String itemId) {
        return null;
    }
}
