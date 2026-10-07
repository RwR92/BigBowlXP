package com.example.BigBowlProjekt.config;


import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import com.example.BigBowlProjekt.model.Product;
import com.example.BigBowlProjekt.repository.LaneRepository;
import com.example.BigBowlProjekt.repository.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class InitData implements CommandLineRunner {

    private static final int NUMBER_OF_BOWLING_LANES = 24;
    private static final int NUMBER_OF_CHILD_LANES = 4;
    private static final int NUMBER_OF_AIRHOCKEY_TABLES = 6;

    private final LaneRepository laneRepository;
    private final ProductRepository productRepository;

    public InitData(LaneRepository laneRepository, ProductRepository productRepository) {
        this.laneRepository = laneRepository;
        this.productRepository = productRepository;
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
    }
}

