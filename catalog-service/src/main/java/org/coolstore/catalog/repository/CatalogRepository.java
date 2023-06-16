package org.coolstore.catalog.repository;

import org.coolstore.catalog.entity.Catalog;
import io.quarkus.hibernate.orm.panache.PanacheRepositoryBase;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.List;

@ApplicationScoped
public class CatalogRepository implements PanacheRepositoryBase<Catalog, String> {


    List<Catalog> getAll(){
        return listAll();
    }

    Catalog getOne(String itemId){
        return findById(itemId);
    }

    void doUpdate(Catalog product){

    }

    void doDelete(String itemId){

    }

}
