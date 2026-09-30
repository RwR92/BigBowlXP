package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.EmployeeDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.BigBowlProjekt.service.EmployeeService;
import com.example.BigBowlProjekt.service.WorkingShiftService;

import java.util.List;

@RestController
public class AdminEmployeeController {

    private final EmployeeService employeeService;
    private final WorkingShiftService workingShiftService;

    public AdminEmployeeController(EmployeeService employeeService, WorkingShiftService workingShiftService) {
        this.employeeService = employeeService;
        this.workingShiftService = workingShiftService;
    }

    // Vi bruger ResponseEntity for at kommunikere med klienten med server response codes
    @GetMapping("/employees/display")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        if (employeeService.getAllEmployees() == null) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
    }

    @GetMapping("/working-shift/display")
    public ResponseEntity<List<WorkingShiftDTO>> showAllWorkingShifts() {

        if (workingShiftService.getAllWorkingShifts() == null) {
            return ResponseEntity.notFound().build();

        } else {

            return ResponseEntity.ok(workingShiftService.getAllWorkingShifts());
        }

    }

}   
