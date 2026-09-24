package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.model.Reservation;

public class ReservationMapper {

    public static ReservationDTO toDTO(Reservation reservation) {
        return new ReservationDTO(
                reservation.getId(),
                reservation.getType(),
                reservation.getStartTime(),
                reservation.getEndTime(),
                reservation.getLanes(),
                reservation.getGuests()
        );
    }
}