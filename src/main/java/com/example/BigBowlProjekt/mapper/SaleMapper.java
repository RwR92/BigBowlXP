package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.SaleResponseDTO;
import com.example.BigBowlProjekt.model.Sale;

public class SaleMapper {

    public static SaleResponseDTO toDTO(Sale sale) {
        return new SaleResponseDTO(
                sale.getId(),
                sale.getDate()
        );
    }
}
