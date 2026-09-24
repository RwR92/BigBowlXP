package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.Reservation;
import com.example.BigBowlProjekt.model.ReservationType;

import java.time.LocalDateTime;
import java.util.List;

public record ReservationDTO(
    Long id,
    ReservationType type,
    LocalDateTime startTime,
    LocalDateTime endTime,
    List<Lane> lanes,
    Integer guests
) {}
