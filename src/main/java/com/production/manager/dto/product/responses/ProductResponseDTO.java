package com.production.manager.dto.product.responses;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import lombok.Data;

@Data
public class ProductResponseDTO {
    private UUID id;
    private Integer code;
    private String name;
    private BigDecimal price;
    private List<ProductMaterialDetailDTO> materials;
}
