package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.LaneDTO;
import com.example.BigBowlProjekt.dto.LaneSummaryDTO;
import com.example.BigBowlProjekt.model.Lane;

public class LaneMapper {

    public static LaneDTO toDTO(Lane lane) {
        return new LaneDTO(
                lane.getId(),
                lane.getLaneNumber(),
                lane.getType(),
                lane.isChildFriendly()
        );
    }

    public static LaneSummaryDTO toSummaryDTO(Lane lane) {
        return new LaneSummaryDTO(
                lane.getId(),
                lane.getLaneNumber(),
                lane.getType()
        );
    }
}