package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.LaneDTO;
import com.example.BigBowlProjekt.service.LaneService;
import org.springframework.web.bind.annotation.*;

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

    @PatchMapping("/{id}/close")
    LaneDTO closeLane(@PathVariable Long id){
        return laneService.close(id);
    }

    @PatchMapping("/{id}/open")
    LaneDTO openLane(@PathVariable Long id){
        return laneService.open(id);
    }
}
