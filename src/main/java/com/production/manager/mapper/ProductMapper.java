package com.production.manager.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import com.production.manager.dto.product.requests.CreateProductDTO;
import com.production.manager.dto.product.responses.ProductMaterialDetailDTO;
import com.production.manager.dto.product.responses.ProductResponseDTO;
import com.production.manager.model.Product;
import com.production.manager.model.ProductCompositions;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(source = "compositions", target = "materials")
    ProductResponseDTO toResponseDTO(Product product);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "code", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "compositions", ignore = true)
    Product toEntity(CreateProductDTO dto);

    default ProductMaterialDetailDTO compositionToMaterialDetail(ProductCompositions composition) {
        if (composition == null || composition.getRawMaterial() == null) {
            return null;
        }

        ProductMaterialDetailDTO dto = new ProductMaterialDetailDTO();
        dto.setMaterialName(composition.getRawMaterial().getName());
        dto.setQuantityRequired(composition.getQuantityRequired());
        return dto;
    }
}
