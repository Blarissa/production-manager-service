package com.production.manager.dto.raw_materials.requests;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class CreateRawMaterialDTO {
    private String name;
    private BigDecimal stockQuantity;
}
