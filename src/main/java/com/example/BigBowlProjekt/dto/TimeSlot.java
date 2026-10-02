package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.LaneType;

import java.time.LocalDateTime;
import java.util.List;

public record TimeSlot(
        LocalDateTime start,
        LocalDateTime end,
        LaneType type,
        List<Integer> availableLaneNumbers
) {
    public int availableCount() {
        return availableLaneNumbers.size();
    }
}