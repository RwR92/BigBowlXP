package com.example.BigBowlProjekt.dto;

import java.util.List;

public record SaleRequestDTO(
        List<SaleItemRequestDTO> saleItems
) {
}
