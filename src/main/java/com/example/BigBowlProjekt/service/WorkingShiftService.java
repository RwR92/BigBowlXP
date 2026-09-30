package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.mapper.WorkingShiftMapper;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.TemporalAmount;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkingShiftService {

    private final WorkingShiftRepository workingShiftRepository;


    public WorkingShiftService(WorkingShiftRepository workingShiftRepository) {
        this.workingShiftRepository = workingShiftRepository;
    }

    public List<WorkingShiftDTO> getAllWorkingShifts() {

        List<com.example.BigBowlProjekt.model.WorkingShift> workingShifts = workingShiftRepository.findAll();

        List<WorkingShiftDTO> workingShiftDTOList = new ArrayList<>();

        for (com.example.BigBowlProjekt.model.WorkingShift shifts : workingShifts) {

            WorkingShiftDTO shiftDTO = WorkingShiftMapper.toDTO(shifts);

            workingShiftDTOList.add(shiftDTO);
        }

        return workingShiftDTOList;
    }

    public List<WorkingShiftDTO> getAllWorkingShiftWeekAhead(LocalDate givenDate) {
        List weekList = new ArrayList<>();
        for  (WorkingShiftDTO shift : getAllWorkingShifts()) {
            if (
                    shift.date().isBefore(givenDate.plusDays(7))
                    && shift.date().isAfter(givenDate)
                    || shift.date().equals(givenDate)
            ) {
                weekList.add(shift);
            }
        }
        return weekList;
    }
}
