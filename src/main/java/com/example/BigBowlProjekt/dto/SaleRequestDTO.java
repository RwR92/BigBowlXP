package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.SaleItem;

import java.time.LocalDate;
import java.util.List;

public record SaleRequestDTO(
        LocalDate saleDate,
        List<SaleItemRequestDTO> saleItems
) {
}
