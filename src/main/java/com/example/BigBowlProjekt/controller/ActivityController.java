package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.ActivityDTO;
import com.example.BigBowlProjekt.service.ActivityService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/activities")
public class ActivityController {

    private final ActivityService activityService;

    public ActivityController(ActivityService activityService) {
        this.activityService = activityService;
    }

    @GetMapping
    public List<ActivityDTO> getAllActivities() {
        return activityService.getAllActivities();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ActivityDTO> getActivityById(@PathVariable Long id) {
        Optional<ActivityDTO> activity = activityService.getActivityById(id);
        if (activity.isPresent()) {
            return ResponseEntity.ok(activity.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ActivityDTO createActivity(@RequestBody ActivityDTO activityDTO) {
        return activityService.createActivity(activityDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteActivity(@PathVariable Long id) {
        Optional<ActivityDTO> byId = activityService.getActivityById(id);

        if (byId.isPresent()) {
            activityService.deleteActivity(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

