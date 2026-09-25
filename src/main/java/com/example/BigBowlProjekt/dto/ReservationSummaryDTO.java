package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.ReservationType;

import java.time.LocalDateTime;

public record ReservationSummaryDTO(
        Long id,
        ReservationType type,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}