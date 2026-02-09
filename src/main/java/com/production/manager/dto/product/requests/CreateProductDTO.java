package com.production.manager.dto.product.requests;

import java.math.BigDecimal;
import java.util.List;

import lombok.Data;
import java.util.UUID;

@Data
public class CreateProductDTO {
    private String name;
    private BigDecimal price;
    private List<ProductMaterialInputDTO> materials;
}

@Data
class ProductMaterialInputDTO {
    private UUID materialId;
    private BigDecimal quantityRequired;
}
