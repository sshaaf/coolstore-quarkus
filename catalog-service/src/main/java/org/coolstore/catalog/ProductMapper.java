package org.coolstore.catalog;


import org.coolstore.catalog.entity.Catalog;
import org.coolstore.catalog.model.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.JAKARTA_CDI)
public interface ProductMapper {

    ProductMapper INSTANCE = Mappers.getMapper(ProductMapper.class);

    @Mapping( target= "quantity", ignore = true)
    Product fromCatalog(Catalog product);

    List<Product> fromCatalog(List<Catalog> products);



}
