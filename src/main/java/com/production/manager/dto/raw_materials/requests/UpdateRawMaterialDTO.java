package com.production.manager.dto.raw_materials.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class UpdateRawMaterialDTO {
    private String name;
    private BigDecimal stockQuantity;
}
