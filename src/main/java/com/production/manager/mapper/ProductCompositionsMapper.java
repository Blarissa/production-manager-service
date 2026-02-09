package com.production.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.production.manager.dto.product_compositions.requests.CreateProductCompositionDTO;
import com.production.manager.dto.product_compositions.responses.ProductCompositionResponseDTO;
import com.production.manager.model.ProductCompositions;

@Mapper(componentModel = "spring")
public interface ProductCompositionsMapper {
    @Mapping(source = "product.id", target = "product.id")
    @Mapping(source = "product.name", target = "product.name")
    @Mapping(source = "rawMaterial.id", target = "rawMaterial.id")
    @Mapping(source = "rawMaterial.name", target = "rawMaterial.name")
    ProductCompositionResponseDTO toResponseDTO(ProductCompositions productCompositions);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "product", ignore = true)
    @Mapping(target = "rawMaterial", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    ProductCompositions toEntity(CreateProductCompositionDTO dto);
}
