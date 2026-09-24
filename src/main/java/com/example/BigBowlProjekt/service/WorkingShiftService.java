package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import java.time.LocalDate;

public class WorkingShiftService {

        public WorkingShift getAllWorkingShiftsByWeek(LocalDate date) {
        return WorkingShiftRepository.getAllWorkingShiftsByWeek(date);
    }
    
}
