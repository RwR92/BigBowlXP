package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.LaneSummaryDTO;
import com.example.BigBowlProjekt.dto.ActivityDTO;
import com.example.BigBowlProjekt.dto.ActivitySummaryDTO;
import com.example.BigBowlProjekt.model.Activity;
import com.example.BigBowlProjekt.model.Lane;

import java.util.ArrayList;
import java.util.List;

public class ActivityMapper {

    public static ActivityDTO toDTO(Activity activity) {
        List<LaneSummaryDTO> laneSummaries = new ArrayList<>();
        for (Lane lane : activity.getLanes()) {
            laneSummaries.add(LaneMapper.toSummaryDTO(lane));
        }

        return new ActivityDTO(
                activity.getId(),
                activity.getType(),
                activity.getStartTime(),
                activity.getEndTime(),
                laneSummaries,
                activity.getGuests()
        );
    }

    public static ActivitySummaryDTO toSummaryDTO(Activity activity) {
        return new ActivitySummaryDTO(
                activity.getId(),
                activity.getType(),
                activity.getStartTime(),
                activity.getEndTime()
        );
    }
}