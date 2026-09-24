package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.Lane;

import java.time.LocalDate;
import java.util.List;

public record Reservation(
    Long id,
    List<Lane> lanes,
    LocalDate startTime,
    LocalDate endTime,
    Integer guest
) {
}
