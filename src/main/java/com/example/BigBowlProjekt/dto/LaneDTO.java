package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.LaneType;

public record LaneDTO(Long id, int laneNumber, LaneType type, boolean childFriendly) {}