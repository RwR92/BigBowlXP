package com.example.BigBowlProjekt.dto;

import java.time.LocalDate;
import java.util.List;

public record SaleResponseDTO(
        Long id,
        LocalDate saleDate,
        List<SaleItemResponseDTO> items) {
}
