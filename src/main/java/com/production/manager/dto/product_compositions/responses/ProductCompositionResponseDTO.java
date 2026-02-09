package com.production.manager.dto.product_compositions.responses;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductCompositionResponseDTO {
    private UUID id;
    private ItemDTO product;
    private ItemDTO rawMaterial;
    private BigDecimal quantityRequired;
}
