package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class WorkingShiftService {

    private final WorkingShiftRepository workingShiftRepository;

    public WorkingShiftService(WorkingShiftRepository workingShiftRepository) {
        this.workingShiftRepository = workingShiftRepository;
    }

    public WorkingShift getAllWorkingShiftsByWeek(LocalDate date) {
        return WorkingShiftRepository.getAllWorkingShiftsByWeek(date);
    }

    public List<WorkingShift> getAllWorkingShifts() {
        return WorkingShiftRepository.getAllWorkingShifts();
    }

}
