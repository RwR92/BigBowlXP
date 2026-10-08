package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.SaleRequestDTO;
import com.example.BigBowlProjekt.dto.SaleResponseDTO;
import com.example.BigBowlProjekt.service.SaleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/sales")
public class SaleController {

    private final SaleService saleService;

    public SaleController(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<SaleResponseDTO> createSale(@RequestBody SaleRequestDTO saleItems){
        System.out.println("Du kom ind i createSale: "+saleItems);
        SaleResponseDTO savedSale = saleService.createSale(saleItems);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedSale);
    }

    @GetMapping
    public ResponseEntity<List<SaleResponseDTO>> getAllSalesWithinLastMonth() {
        return ResponseEntity.ok(saleService.getAllSalesWithinLastMonth());
    }

    @GetMapping("/specific-month")
    public ResponseEntity<List<SaleResponseDTO>> getAllSalesFromSpecificMonth(
            @RequestParam int year,
            @RequestParam int month) {
        return ResponseEntity.ok(saleService.getAllSalesFromSpecificMonth(year, month));
    }
}
