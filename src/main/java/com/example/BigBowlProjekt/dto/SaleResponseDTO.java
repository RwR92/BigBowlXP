package com.example.BigBowlProjekt.dto;

import java.time.LocalDate;

public record SaleResponseDTO(
        Long id,
        LocalDate date
) {
}
