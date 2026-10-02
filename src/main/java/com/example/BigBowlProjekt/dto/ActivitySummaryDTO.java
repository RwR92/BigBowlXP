package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.ActivityType;

import java.time.LocalDateTime;

public record ActivitySummaryDTO(
        Long id,
        ActivityType type,
        LocalDateTime startTime,
        LocalDateTime endTime
) {}