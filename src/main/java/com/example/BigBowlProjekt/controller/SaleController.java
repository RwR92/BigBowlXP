package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.SaleResponseDTO;
import com.example.BigBowlProjekt.service.SaleService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSalesWithinLastMonth() {
        return ResponseEntity.ok(saleService.getAllSalesWithinLastMonth());
    }
}
