package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.LaneType;

public record LaneSummaryDTO(Long id, int laneNumber, LaneType type) {}
