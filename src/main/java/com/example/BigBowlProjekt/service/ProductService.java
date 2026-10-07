package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.ProductResponseDTO;
import com.example.BigBowlProjekt.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductResponseDTO> getAllProducts() {
        return productRepository.findAll().stream()
                .map(p -> new ProductResponseDTO(p.getId(), p.getName(), p.getPrice()))
                .toList();
    }
}
