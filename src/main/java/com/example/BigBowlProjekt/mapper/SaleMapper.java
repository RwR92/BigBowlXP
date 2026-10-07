package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.SaleItemRequestDTO;
import com.example.BigBowlProjekt.dto.SaleItemResponseDTO;
import com.example.BigBowlProjekt.dto.SaleRequestDTO;
import com.example.BigBowlProjekt.dto.SaleResponseDTO;
import com.example.BigBowlProjekt.model.Product;
import com.example.BigBowlProjekt.model.Sale;
import com.example.BigBowlProjekt.model.SaleItem;
import com.example.BigBowlProjekt.repository.ProductRepository;

import java.time.LocalDate;
import java.util.List;

public class SaleMapper {


    public static SaleResponseDTO toResponse(Sale sale){
        List<SaleItemResponseDTO> items = sale.getSaleItems().stream()
                .map(item -> new SaleItemResponseDTO(
                        item.getProduct().getName(),
                        item.getQuantity(),
                        item.getPrice()
                )).toList();
        return new SaleResponseDTO(sale.getId(), sale.getSaleDate(), items);
    }

    public static Sale toModel(SaleRequestDTO request, ProductRepository productRepository){
        Sale sale = new Sale();
        sale.setSaleDate(LocalDate.now());

        for(SaleItemRequestDTO itemRequest : request.saleItems()){
            Product product = productRepository.findById(itemRequest.productId())
                    .orElseThrow(() -> new IllegalArgumentException("Produkt findes ikke: "+ itemRequest.productId()));

            SaleItem item =  new SaleItem();
            item.setProduct(product);
            item.setQuantity(itemRequest.quantity());
            item.setPrice(product.getPrice());

            sale.addSaleItem(item);
        }
        return sale;
    }
}
