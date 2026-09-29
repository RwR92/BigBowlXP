package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftFormDTO;

import java.util.List;
import java.util.Optional;

public interface WorkingShiftService {

    List<WorkingShiftFormDTO> getAllWorkingShifts();

    Optional<WorkingShiftFormDTO> getWorkingShiftById(Long id);

    WorkingShiftFormDTO createWorkingShift (WorkingShiftFormDTO dto);

    Optional<WorkingShiftFormDTO> updateWorkingShift(Long id, WorkingShiftFormDTO dto);

    boolean deleteWorkingShift(Long id);


}


