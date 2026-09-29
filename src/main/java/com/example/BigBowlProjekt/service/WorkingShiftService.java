package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface WorkingShiftService {

 List<WorkingShiftDTO> getAllWorkingShifts();

}
