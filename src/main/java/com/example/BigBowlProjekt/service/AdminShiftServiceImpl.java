package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;
import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.AdminShiftRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminShiftServiceImpl implements AdminShiftService {

    private final AdminShiftRepository adminShiftRepository;

    public AdminShiftServiceImpl(AdminShiftRepository adminShiftRepository) {
        this.adminShiftRepository = adminShiftRepository;
    }

    @Override
    public WorkingShiftDTO createWorkingShift(WorkingShiftRequest dto) {

        WorkingShift workingShift = new WorkingShift(
                dto.date(),
                dto.startTime(),
                dto.endTime()
        );

        WorkingShift saved = adminShiftRepository.save(workingShift);

        return new WorkingShiftDTO(
                saved.getWorkingShiftId(),
                saved.getDate(),
                saved.getStartTime(),
                saved.getEndTime()
        );
    }

    @Override
    public Optional<WorkingShiftDTO> updateWorkingShift(
            Long id,
            WorkingShiftDTO dto) {

        return adminShiftRepository.findById(id).map(workingShift -> {

            //workingShift.setEmployeeId(dto.employeeId());
            workingShift.setDate(dto.date());
            workingShift.setStartTime(dto.startTime());
            workingShift.setEndTime(dto.endTime());

            WorkingShift saved = adminShiftRepository.save(workingShift);

            return new WorkingShiftDTO(
                    saved.getWorkingShiftId(),
                    //saved.getEmployeeId(),
                    saved.getDate(),
                    saved.getStartTime(),
                    saved.getEndTime()
            );
        });
    }

    @Override
    public boolean deleteWorkingShift(Long id) {

        if (!adminShiftRepository.existsById(id)) {
            return false;
        }

        adminShiftRepository.deleteById(id);
        return true;
    }
}
