package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.SaleResponseDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SaleController {

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSalesWithinLastMonth() {
        return ResponseEntity.ok(saleService.getAllSalesWithinLastMonth());
    }
}
