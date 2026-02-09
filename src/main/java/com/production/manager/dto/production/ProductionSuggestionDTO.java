package com.production.manager.dto.production;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ProductionSuggestionDTO {
    private String productName;
    private Integer maxPossibleQuantity;
    private BigDecimal potentialRevenue;
}
