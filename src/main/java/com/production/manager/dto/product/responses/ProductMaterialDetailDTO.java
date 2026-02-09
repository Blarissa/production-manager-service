package com.production.manager.dto.product.responses;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class ProductMaterialDetailDTO {
    private String materialName;
    private BigDecimal quantityRequired;
}
