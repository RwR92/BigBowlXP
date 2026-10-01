package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;
import com.example.BigBowlProjekt.exception.WorkingShiftNotFoundException;
import com.example.BigBowlProjekt.mapper.WorkingShiftMapper;
import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkingShiftTempService {

    private final WorkingShiftRepository workingShiftRepository;

    public WorkingShiftTempService(WorkingShiftRepository workingShiftRepository) {
        this.workingShiftRepository = workingShiftRepository;
    }

    public WorkingShiftDTO createWorkingShift(WorkingShiftRequest workingShiftRequest) {
        if (workingShiftRequest.startTime().isAfter(workingShiftRequest.endTime())) {
            throw new IllegalArgumentException
                    ("Start time is after end time:"
                            + workingShiftRequest.startTime()
                            + " "
                            + workingShiftRequest.endTime());
        }

        // remember employee validation

        WorkingShift newWorkShift = new WorkingShift(
                workingShiftRequest.date(),
                workingShiftRequest.startTime(),
                workingShiftRequest.endTime()
        );
        workingShiftRepository.save(newWorkShift);
        return WorkingShiftMapper.toDTO(newWorkShift);
    }

    public void deleteWorkingShift(WorkingShiftDTO workingShiftDTO) {
        WorkingShift workingShift = workingShiftRepository.findById(workingShiftDTO.id())
                .orElseThrow(() -> new WorkingShiftNotFoundException(
                        "Working Shift Not found with id: " + workingShiftDTO.id()
                ));

        workingShiftRepository.delete(workingShift);
    }

    public WorkingShiftDTO editWorkingShift(WorkingShiftDTO workingShiftDTO) {
        WorkingShift workingShift = workingShiftRepository.findById(workingShiftDTO.id())
                .orElseThrow(() -> new WorkingShiftNotFoundException(
                        "Working Shift not found with id: " + workingShiftDTO.id()
                ));

    }
}
