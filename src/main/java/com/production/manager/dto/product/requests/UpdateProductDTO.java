package com.production.manager.dto.product.requests;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class UpdateProductDTO {
    private String name;
    private BigDecimal price;
    private List<ProductMaterialUpdateDTO> materials;
}

@Data
class ProductMaterialUpdateDTO {
    private UUID materialId;
    private BigDecimal quantityRequired;
}
