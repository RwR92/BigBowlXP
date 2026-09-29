package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.mapper.WorkingShiftMapper;
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

        List<WorkingShift> workingShifts = workingShiftRepository.findAll();

        List<WorkingShiftDTO> workingShiftDTOList = new ArrayList<>();

        for (WorkingShift shifts : workingShifts) {

            WorkingShiftDTO shiftDTO = WorkingShiftMapper.toDTO(shifts);

            workingShiftDTOList.add(shiftDTO);
        }

        return workingShiftDTOList;
    }


}
