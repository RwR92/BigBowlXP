package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class WorkingShiftService {

    public WorkingShift getAllWorkingShiftsByWeek(LocalDate date) {
        return WorkingShiftRepository.getAllWorkingShiftsByWeek(date);
    }

    public WorkingShift getAllWorkingShifts() {
        return WorkingShiftRepository.getAllWorkingShifts();
    }

}
