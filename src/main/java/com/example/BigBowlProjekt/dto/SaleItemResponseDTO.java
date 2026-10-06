package com.example.BigBowlProjekt.dto;

import java.math.BigDecimal;

public record SaleItemResponseDTO(
        String name,
        Integer quantity,
        BigDecimal price) {
}
