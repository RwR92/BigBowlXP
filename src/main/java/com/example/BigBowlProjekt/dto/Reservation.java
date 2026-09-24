package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.Lane;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public record Reservation(
    Long id,
    List<Lane> lanes,
    LocalDateTime startTime,
    LocalDateTime endTime,
    Integer guest
) {
}
