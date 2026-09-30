package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.LaneDTO;
import com.example.BigBowlProjekt.service.LaneService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("api/lanes")
public class LaneController {

    private final LaneService laneService;

    public LaneController(LaneService laneService) {
        this.laneService = laneService;
    }

    @GetMapping
    public List<LaneDTO> findAllBowlingLanes(){
        return laneService.getAllBowlingLanes();
    }
}
