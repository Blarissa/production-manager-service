package com.production.manager.dto.product.responses;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductCompositionsResponseDTO {
    private UUID id;
    private UUID productId;
    private UUID rawMaterialId;
    private BigDecimal quantity;
}
