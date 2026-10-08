package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;
import com.example.BigBowlProjekt.exception.NotFoundException;
import com.example.BigBowlProjekt.exception.WorkingShiftOverlapException;
import com.example.BigBowlProjekt.mapper.WorkingShiftMapper;
import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class WorkingShiftService {

    private final WorkingShiftRepository workingShiftRepository;
    private final EmployeeRepository employeeRepository;

    public WorkingShiftService(WorkingShiftRepository workingShiftRepository, EmployeeRepository employeeRepository) {
        this.workingShiftRepository = workingShiftRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<WorkingShiftDTO> getAllWorkingShifts() {
        return workingShiftRepository.findAll()
                .stream()
                .map(WorkingShiftMapper::toDTO)
                .toList();
    }

    public List<WorkingShiftDTO> getAllWorkingShiftWeekAhead(LocalDate givenDate) {
        List<WorkingShiftDTO> weekList = new ArrayList<>();
        for (WorkingShiftDTO shift : getAllWorkingShifts()) {
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

    public WorkingShiftDTO createWorkingShift(WorkingShiftRequest workingShiftRequest) {
        if (workingShiftRequest.startTime().isAfter(workingShiftRequest.endTime())) {
            throw new IllegalArgumentException(
                    "Start time is after end time:"
                            + workingShiftRequest.startTime()
                            + " "
                            + workingShiftRequest.endTime());
        }

        List<WorkingShift> workingShifts = workingShiftRepository.getAllByEmployee_EmployeeId(
                workingShiftRequest.employeeId());

        for (WorkingShift w : workingShifts) {
            if (overlaps(workingShiftRequest.startTime(), workingShiftRequest.endTime(), w)) {
                throw new WorkingShiftOverlapException(
                        "Employee already has a shift at this time: "
                                + w.getStartTime()
                                + " "
                                + w.getEndTime());
            }
        }

        Employee employee = employeeRepository.findById(workingShiftRequest.employeeId())
                .orElseThrow(() -> new NotFoundException(
                        "Employee not found with id: " + workingShiftRequest.employeeId()
                ));

        WorkingShift newWorkShift = new WorkingShift(
                workingShiftRequest.date(),
                workingShiftRequest.startTime(),
                workingShiftRequest.endTime(),
                employee
        );
        workingShiftRepository.save(newWorkShift);
        return WorkingShiftMapper.toDTO(newWorkShift);
    }

    public void deleteWorkingShift(Long id) {
        if (!workingShiftRepository.existsById(id)) {
            throw new NotFoundException(
                    "Working Shift not found with id: " + id
            );
        }

        workingShiftRepository.deleteById(id);
    }

    public WorkingShiftDTO editWorkingShift(Long id, WorkingShiftRequest request) {
        WorkingShift workingShift = workingShiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Working Shift not found with id: " + id
                ));

        if (request.startTime().isAfter(request.endTime())) {
            throw new IllegalArgumentException(
                    "Start time is after end time:"
                            + request.startTime()
                            + " "
                            + request.endTime());
        }

        List<WorkingShift> workingShifts = workingShiftRepository.getAllByEmployee_EmployeeId(
                request.employeeId());

        for (WorkingShift w : workingShifts) {
            if (w.getWorkingShiftId().equals(id)) {
                continue;
            }

            if (overlaps(request.startTime(), request.endTime(), w)) {
                throw new WorkingShiftOverlapException(
                        "Employee already has a shift at this time: "
                                + w.getStartTime()
                                + " "
                                + w.getEndTime());
            }
        }

        Employee employee = employeeRepository.findById(request.employeeId())
                .orElseThrow(() -> new NotFoundException(
                        "Employee not found with id: " + request.employeeId()
                ));

        workingShift.setDate(request.date());
        workingShift.setEmployee(employee);
        workingShift.setStartTime(request.startTime());
        workingShift.setEndTime(request.endTime());

        workingShiftRepository.save(workingShift);
        return WorkingShiftMapper.toDTO(workingShift);
    }

    public WorkingShiftDTO getWorkingShiftById(Long id) {
        WorkingShift workingShift = workingShiftRepository.findById(id)
                .orElseThrow(() -> new NotFoundException(
                        "Working shift not found with id: " + id
                ));

        return WorkingShiftMapper.toDTO(workingShift);
    }

    public boolean overlaps(LocalTime newStart, LocalTime newEnd, WorkingShift existingShift) {
        return newStart.isBefore(existingShift.getEndTime())
                && newEnd.isAfter(existingShift.getStartTime());
    }
}


