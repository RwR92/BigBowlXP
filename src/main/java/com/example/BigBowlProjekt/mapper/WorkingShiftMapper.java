package com.example.BigBowlProjekt.mapper;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.model.WorkingShift;

public class WorkingShiftMapper {

    public static WorkingShiftDTO toDTO(WorkingShift workingShift) {
        return new WorkingShiftDTO(
                workingShift.getWorkingShiftId(),
                workingShift.getDate(),
                workingShift.getStartTime(),
                workingShift.getEndTime(),
                EmployeeMapper.toDTO(workingShift.getEmployee())
        );
    }
}
