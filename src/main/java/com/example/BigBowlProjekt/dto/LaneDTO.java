package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;

public record LaneDTO(Long id, int laneNumber, LaneType type, boolean childFriendly, Boolean isOpen) {

    public static LaneDTO from(Lane lane) {
        return new LaneDTO(
                lane.getId(),
                lane.getLaneNumber(),
                lane.getType(),
                lane.isChildFriendly(),
                lane.getIsOpen()
        );
    }
}