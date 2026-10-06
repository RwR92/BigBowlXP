package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.SaleRequestDTO;
import com.example.BigBowlProjekt.dto.SaleResponseDTO;
import com.example.BigBowlProjekt.mapper.SaleMapper;
import com.example.BigBowlProjekt.model.Sale;
import com.example.BigBowlProjekt.repository.ProductRepository;
import com.example.BigBowlProjekt.repository.SaleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.YearMonth;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    public List<SaleResponseDTO> getAllSalesWithinLastMonth() {
        return saleRepository.findAll()
                .stream()
                .filter(sale -> {
                    LocalDate now = LocalDate.now();
                    LocalDate salesDate = sale.getSaleDate();

                    return salesDate.isAfter(now.minusMonths(1))
                            && salesDate.isBefore(now)
                            || salesDate.equals(now);
                })
                .map(SaleMapper::toResponse)
                .toList();
    }

    public List<SaleResponseDTO> getAllSalesFromSpecificMonth(int year, int month) {
        YearMonth yearMonth = YearMonth.of(year, month);

        LocalDate startDate = yearMonth.atDay(1);
        LocalDate endDate = yearMonth.atEndOfMonth();

        return saleRepository.getAllBySaleDateIsBetween(startDate, endDate)
                .stream()
                .map(SaleMapper::toResponse)
                .toList();
    }

    public SaleResponseDTO createSale(SaleRequestDTO sale){
        Sale savedSale = saleRepository.save(SaleMapper.toModel(sale, productRepository));
        return SaleMapper.toResponse(savedSale);
    }
}