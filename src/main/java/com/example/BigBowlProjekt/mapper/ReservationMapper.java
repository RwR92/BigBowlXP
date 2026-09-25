package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.LaneSummaryDTO;
import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.dto.ReservationSummaryDTO;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        List<LaneSummaryDTO> laneSummaries = new ArrayList<>();
        for (Lane lane : reservation.getLanes()) {
            laneSummaries.add(LaneMapper.toSummaryDTO(lane));
        }

        return new ReservationDTO(
                reservation.getId(),
                reservation.getType(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                laneSummaries,
                reservation.getGuests()
        );
    }

    public static ReservationSummaryDTO toSummaryDTO(Reservation reservation) {
        return new ReservationSummaryDTO(
                reservation.getId(),
                reservation.getType(),
                reservation.getStartTime(),
                reservation.getEndTime()
        );
    }
}