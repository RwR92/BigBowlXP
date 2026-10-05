package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.CalendarActivityDTO;
import com.example.BigBowlProjekt.repository.ActivityRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CalendarService {

    private final ActivityRepository activityRepository;

    public CalendarService(ActivityRepository activityRepository) {
        this.activityRepository = activityRepository;
    }

    public List<CalendarActivityDTO> getActivitiesBetween(
                LocalDateTime from,
                LocalDateTime to) {

        return activityRepository
                .findByStartTimeLessThanAndEndTimeGreaterThan(to, from)
                .stream()
                .map(activity -> new CalendarActivityDTO(
                        activity.getId(),
                        activity.getType(),
                        activity.getStartTime(),
                        activity.getEndTime()
                ))
                .toList();
    }

}
