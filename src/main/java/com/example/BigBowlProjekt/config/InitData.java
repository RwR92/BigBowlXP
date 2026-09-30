package com.example.BigBowlProjekt.config;


import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import com.example.BigBowlProjekt.repository.LaneRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class InitData implements CommandLineRunner {

    private static final int NUMBER_OF_BOWLING_LANES = 24;
    private static final int NUMBER_OF_CHILD_LANES = 4;

    private final LaneRepository laneRepository;

    public InitData(LaneRepository laneRepository) {
        this.laneRepository = laneRepository;
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
    }
}

