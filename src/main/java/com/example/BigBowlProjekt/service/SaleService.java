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
        List<SaleResponseDTO> salesList = new ArrayList<>();
        for (Sale sale : saleRepository.findAll()) {
            /* To shorten the text within the if statement and make it more readable
             we add these two references */
            LocalDate now = LocalDate.now();
            LocalDate salesDate = sale.getDate();

            if (salesDate.isAfter(now.minusMonths(1)) && salesDate.isBefore(now) || salesDate.equals(now)) {
                SaleResponseDTO saleResponseDTO = SaleMapper.toDTO(sale);
                salesList.add(saleResponseDTO);
            }
        }
        return salesList;
    }

    public SaleResponseDTO createSale(SaleRequestDTO sale){
        Sale savedSale = saleRepository.save(SaleMapper.toModel(sale, productRepository));
        return SaleMapper.toResponse(savedSale);
    }
}