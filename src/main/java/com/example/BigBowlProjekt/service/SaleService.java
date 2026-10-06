package com.example.BigBowlProjekt.service;

import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class SaleService {

    private final SaleRepository saleRepository;
    private final SaleMapper saleMapper;

    public SaleService(SaleRepository saleRepository, SaleMapper saleMapper){
        this.saleRepository = saleRepository;
        this.saleMapper = saleMapper;
    }

    public SaleResponseDTO createSale(SaleRequestDTO sale){
        Sale saleModel = saleMapper.toModel(sale);
        Sale savedSale = saleRepository.save(saleModel);
        return saleMapper.toResponse(savedSale);
    }
}
