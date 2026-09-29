package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class WorkingShiftImpl implements WorkingShiftService {

    private final WorkingShiftRepository workingShiftRepository;


    public WorkingShiftImpl(WorkingShiftRepository workingShiftRepository) {
        this.workingShiftRepository = workingShiftRepository;
    }

    @Override
    public List<WorkingShiftDTO> getAllWorkingShifts() {
        List<WorkingShiftDTO> result = new ArrayList<>();
        for (WorkingShift shift : workingShiftRepository.findAll()) {
            result.add(toDTO(shift));
        }
        return result;
    }

    private WorkingShiftDTO toDTO(WorkingShift shift) {
        return new WorkingShiftDTO(
                shift.getWorkingShiftId(),
                shift.getDate(),
                shift.getStartTime(),
                shift.getEndTime()
        );
    }

}
