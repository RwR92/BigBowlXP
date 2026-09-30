package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftFormDTO;

import java.util.Optional;

public interface AdminShiftService {

    WorkingShiftFormDTO createWorkingShift(WorkingShiftFormDTO dto);

    Optional<WorkingShiftFormDTO> updateWorkingShift(Long id, WorkingShiftFormDTO dto);

    boolean deleteWorkingShift(Long id);
}
