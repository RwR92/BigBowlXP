package com.example.BigBowlProjekt.config;


import com.example.BigBowlProjekt.model.*;
import com.example.BigBowlProjekt.repository.LaneRepository;
import com.example.BigBowlProjekt.repository.ProductRepository;
import com.example.BigBowlProjekt.repository.SaleRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Component
public class InitData implements CommandLineRunner {

    private static final int NUMBER_OF_BOWLING_LANES = 24;
    private static final int NUMBER_OF_CHILD_LANES = 4;
    private static final int NUMBER_OF_AIRHOCKEY_TABLES = 6;

    private final LaneRepository laneRepository;
    private final ProductRepository productRepository;
    private final SaleRepository saleRepository;

    public InitData(LaneRepository laneRepository, ProductRepository productRepository, SaleRepository saleRepository) {
        this.laneRepository = laneRepository;
        this.productRepository = productRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        if (laneRepository.count() > 0) {
            return;
        }

        int lastNormalLane = NUMBER_OF_BOWLING_LANES - NUMBER_OF_CHILD_LANES;

        for (int laneNumber = 1; laneNumber <= NUMBER_OF_BOWLING_LANES; laneNumber++) {

            boolean childFriendly;

            if (laneNumber > lastNormalLane) {
                childFriendly = true;
            } else {
                childFriendly = false;
            }

            Lane lane = new Lane(laneNumber, LaneType.BOWLING, childFriendly);
            laneRepository.save(lane);
        }
        for (int tableNumber = 1; tableNumber <= NUMBER_OF_AIRHOCKEY_TABLES; tableNumber++) {
            Lane table = new Lane(tableNumber, LaneType.AIRHOCKEY, false);
            laneRepository.save(table);
        }


        // sale injection
        Product p1 = new Product("Øl", new BigDecimal(50));
        Product p2 = new Product("Sodavand", new BigDecimal(35));
        productRepository.save(p1);
        productRepository.save(p2);

        Sale sale1 = new Sale();
        sale1.setSaleDate(LocalDate.of(2026, 10, 1));

        SaleItem item1 = new SaleItem();
        item1.setSale(sale1);
        item1.setProduct(p1); // Øl
        item1.setQuantity(3);
        item1.setPrice(p1.getPrice());

        SaleItem item2 = new SaleItem();
        item2.setSale(sale1);
        item2.setProduct(p2); // Sodavand
        item2.setQuantity(2);
        item2.setPrice(p2.getPrice());

        sale1.setSaleItems(List.of(item1, item2));


        Sale sale2 = new Sale();
        sale2.setSaleDate(LocalDate.of(2026, 10, 2));

        SaleItem item3 = new SaleItem();
        item3.setSale(sale2);
        item3.setProduct(p1); // Øl
        item3.setQuantity(5);
        item3.setPrice(p1.getPrice());

        sale2.setSaleItems(List.of(item3));


        Sale sale3 = new Sale();
        sale3.setSaleDate(LocalDate.of(2026, 10, 3));

        SaleItem item4 = new SaleItem();
        item4.setSale(sale3);
        item4.setProduct(p2); // Sodavand
        item4.setQuantity(4);
        item4.setPrice(p2.getPrice());

        SaleItem item5 = new SaleItem();
        item5.setSale(sale3);
        item5.setProduct(p1); // Øl
        item5.setQuantity(2);
        item5.setPrice(p1.getPrice());

        sale3.setSaleItems(List.of(item4, item5));


        Sale sale4 = new Sale();
        sale4.setSaleDate(LocalDate.of(2026, 10, 5));

        SaleItem item6 = new SaleItem();
        item6.setSale(sale4);
        item6.setProduct(p1); // Øl
        item6.setQuantity(10);
        item6.setPrice(p1.getPrice());

        sale4.setSaleItems(List.of(item6));


        Sale sale5 = new Sale();
        sale5.setSaleDate(LocalDate.of(2026, 10, 7));

        SaleItem item7 = new SaleItem();
        item7.setSale(sale5);
        item7.setProduct(p2); // Sodavand
        item7.setQuantity(6);
        item7.setPrice(p2.getPrice());

        SaleItem item8 = new SaleItem();
        item8.setSale(sale5);
        item8.setProduct(p1); // Øl
        item8.setQuantity(4);
        item8.setPrice(p1.getPrice());

        sale5.setSaleItems(List.of(item7, item8));

        saleRepository.save(sale1);
        saleRepository.save(sale2);
        saleRepository.save(sale3);
        saleRepository.save(sale4);
        saleRepository.save(sale5);
    }
}

