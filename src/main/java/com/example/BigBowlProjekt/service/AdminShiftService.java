package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;

import java.util.Optional;

public interface AdminShiftService {

    WorkingShiftDTO createWorkingShift(WorkingShiftRequest dto);

    Optional<WorkingShiftDTO> updateWorkingShift(Long id, WorkingShiftDTO dto);

    boolean deleteWorkingShift(Long id);
}
