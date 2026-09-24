package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.dto.ReservationSummaryDTO;
import com.example.BigBowlProjekt.model.Reservation;

import java.util.ArrayList;
import java.util.List;

public class ReservationMapper {
    public static ReservationDTO toDTO(Reservation reservation) {
        List<ReservationSummaryDTO> reservationSummaries = new ArrayList<>();
        for (Reservation reservation1 : reservation.getReservations()) {
            reservationSummaries.add(toReservationSummaryDTO(reservation));
        }

        return new ReservationDTO(
                reservation.getId(),
                reservation.getType(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getGuests()
        );
    }

    private static ReservationSummaryDTO toReservationSummaryDTO(Reservation reservation) {
        return new ReservationSummaryDTO(
                reservation.getId(),
                reservation.getType(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getGuests()
        );
    }
}
