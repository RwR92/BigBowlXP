package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.EmployeeDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BigBowlProjekt.service.EmployeeService;
import com.example.BigBowlProjekt.service.WorkingShiftService;

import java.time.LocalDate;
import java.util.List;

// program kan nu køre uanset port
@CrossOrigin(origins = "*")

@RequestMapping("/api")
@RestController
public class AdminEmployeeController {

    private final EmployeeService employeeService;
    private final WorkingShiftService workingShiftService;

    public AdminEmployeeController(EmployeeService employeeService, WorkingShiftService workingShiftService) {
        this.employeeService = employeeService;
        this.workingShiftService = workingShiftService;
    }

    @GetMapping("/employees")
    public ResponseEntity<List<EmployeeDTO>> getAllEmployees() {

        if (employeeService.getAllEmployees() == null) {
            return ResponseEntity.notFound().build();

        } else {
            return ResponseEntity.ok(employeeService.getAllEmployees());
        }
    }

    @GetMapping("/working-shift/{givenDate}")
    public ResponseEntity<List<WorkingShiftDTO>> get(@PathVariable int givenDate) {
        int day = givenDate % 100;
        int month = (givenDate % 10000) / 100;
        int year = givenDate / 10000;
        List<WorkingShiftDTO> list = workingShiftService.getAllWorkingShiftWeekAhead(LocalDate.of(year, month, day));
        return ResponseEntity.ok(list);
    }

    @GetMapping("/working-shift")
    public ResponseEntity<List<WorkingShiftDTO>> showAllWorkingShifts() {
        List<WorkingShiftDTO> workingShiftList = workingShiftService.getAllWorkingShifts();

        if (workingShiftList == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(workingShiftList);
        }

    }

}   
