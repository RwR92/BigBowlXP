package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.ActivityType;

import java.time.LocalDateTime;
import java.util.List;

public record ActivityDTO(
        Long id,
        ActivityType type,
        LocalDateTime startTime,
        LocalDateTime endTime,
        List<LaneSummaryDTO> lanes,
        Integer guests
) {
}