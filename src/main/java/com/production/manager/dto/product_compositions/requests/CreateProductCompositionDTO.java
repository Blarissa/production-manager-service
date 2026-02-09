package com.production.manager.dto.product_compositions.requests;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class CreateProductCompositionDTO {
    private UUID productId;
    private UUID materialId;
    private BigDecimal quantityRequired;
}
