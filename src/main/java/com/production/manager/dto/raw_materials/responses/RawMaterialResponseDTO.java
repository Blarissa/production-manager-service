package com.production.manager.dto.raw_materials.responses;

import java.math.BigDecimal;
import java.util.UUID;

import lombok.Data;

@Data
public class RawMaterialResponseDTO {
    private UUID id;
    private Integer code;
    private String name;
    private BigDecimal stockQuantity;
}
