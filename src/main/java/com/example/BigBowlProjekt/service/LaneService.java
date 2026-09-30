package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.LaneDTO;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import com.example.BigBowlProjekt.repository.LaneRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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
}
