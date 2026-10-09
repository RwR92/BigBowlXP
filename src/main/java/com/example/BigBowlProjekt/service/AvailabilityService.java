package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.TimeSlot;
import com.example.BigBowlProjekt.model.Activity;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import com.example.BigBowlProjekt.repository.ActivityRepository;
import com.example.BigBowlProjekt.repository.LaneRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AvailabilityService {


    private static final LocalTime OPENING_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLOSING_TIME = LocalTime.of(23, 0);

    private final LaneRepository laneRepository;
    private final ActivityRepository activityRepository;
    private final ClubSchedule clubSchedule;

    public AvailabilityService(LaneRepository laneRepository,
                               ActivityRepository activityRepository,
                               ClubSchedule clubSchedule) {
        this.laneRepository = laneRepository;
        this.activityRepository = activityRepository;
        this.clubSchedule = clubSchedule;
    }

    // Ledighed for ét bestemt, valgfrit tidsrum
    public TimeSlot getAvailability(LaneType type, LocalDateTime start, LocalDateTime end) {

        List<Lane> allLanes = laneRepository.findByType(type);
        List<Activity> allActivities = activityRepository.findAll();

        return buildTimeSlot(type, start, end, allLanes, allActivities);
    }

    // Ledighed for en hel dag, opdelt i 1-times slots
    public List<TimeSlot> getDayAvailability(LaneType type, LocalDate date) {

        List<Lane> allLanes = laneRepository.findByType(type);
        List<Activity> allActivities = activityRepository.findAll();

        List<TimeSlot> daySlots = new ArrayList<>();

        LocalTime current = OPENING_TIME;
        while (current.isBefore(CLOSING_TIME)) {

            LocalDateTime slotStart = date.atTime(current);
            LocalDateTime slotEnd = slotStart.plusHours(1);

            daySlots.add(buildTimeSlot(type, slotStart, slotEnd, allLanes, allActivities));

            current = current.plusHours(1);
        }

        return daySlots;
    }

    private TimeSlot buildTimeSlot(LaneType type, LocalDateTime start, LocalDateTime end,
                                   List<Lane> allLanes, List<Activity> allActivities) {

        List<Integer> availableNumbers = new ArrayList<>();

        for (Lane lane : allLanes) {

            if (clubSchedule.isClubReserved(lane, start, end)) {
                continue;
            }

            if (isAlreadyBooked(lane, allActivities, start, end)) {
                continue;
            }

            availableNumbers.add(lane.getLaneNumber());
        }

        return new TimeSlot(start, end, type, availableNumbers);
    }

    private boolean isAlreadyBooked(Lane lane, List<Activity> allActivities,
                                    LocalDateTime start, LocalDateTime end) {

        for (Activity activity : allActivities) {

            if (!activity.overlaps(start, end)) {
                continue;
            }

            for (Lane bookedLane : activity.getLanes()) {
                if (bookedLane.getId().equals(lane.getId())) {
                    return true;
                }
            }
        }

        return false;
    }
}