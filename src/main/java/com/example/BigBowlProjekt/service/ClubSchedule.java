package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.Lane;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Component
public class ClubSchedule {

    private static final int CLUB_MAX_LANE_NUMBER = 14;
    private static final LocalTime CLUB_START = LocalTime.of(10, 0);
    private static final LocalTime CLUB_END = LocalTime.of(17, 0);

    public boolean isClubReserved(Lane lane, LocalDateTime start, LocalDateTime end) {

        if (lane.getLaneNumber() > CLUB_MAX_LANE_NUMBER) {
            return false;
        }

        DayOfWeek day = start.getDayOfWeek();
        if (day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY) {
            return false;
        }

        LocalDateTime clubStart = start.toLocalDate().atTime(CLUB_START);
        LocalDateTime clubEnd = start.toLocalDate().atTime(CLUB_END);

        return start.isBefore(clubEnd) && end.isAfter(clubStart);
    }
}