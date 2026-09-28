package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WorkingShiftImpl implements WorkingShiftService {

    private final WorkingShiftRepository workingShiftRepository;


    public WorkingShiftImpl(WorkingShiftRepository workingShiftRepository) {
        this.workingShiftRepository = workingShiftRepository;
    }

    @Override
    public List<WorkingShiftDTO> getAllWorkingShifts() {
        return List.of();
    }
}
