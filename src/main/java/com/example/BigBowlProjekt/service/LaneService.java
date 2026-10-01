package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.LaneDTO;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import com.example.BigBowlProjekt.repository.LaneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class LaneService {

    private final LaneRepository laneRepository;

    public LaneService(LaneRepository laneRepository) {
        this.laneRepository = laneRepository;
    }

    @Transactional
    public List<LaneDTO> getAllBowlingLanes() {
        List<Lane> lanes = laneRepository.findByType(LaneType.BOWLING);
        List<LaneDTO> laneDTOs = new ArrayList<>();
        for (Lane lane : lanes) {
            laneDTOs.add(LaneDTO.from(lane));
        }
        return laneDTOs;
    }

    public LaneDTO close(Long id){
        Lane lane = findLane(id);
        lane.setIsOpen(false);
        Lane savedLane = laneRepository.save(lane);

        return LaneDTO.from(savedLane);
    }

    public LaneDTO open(Long id){
        Lane lane = findLane(id);
        lane.setIsOpen(true);
        Lane savedLane = laneRepository.save(lane);

        return LaneDTO.from(savedLane);
    }

    private Lane findLane(Long id){
        Optional<Lane> laneById = laneRepository.findById(id);
        if(laneById.isEmpty()) {
            // Skal laves om til at bruge vores egen exception
            throw new RuntimeException();
        }
        return laneById.get();
    }
}
