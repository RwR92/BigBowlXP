package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.EmployeeDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.BigBowlProjekt.service.EmployeeService;
import com.example.BigBowlProjekt.service.WorkingShiftService;

import java.util.List;

// program kan nu køre uanset port
@CrossOrigin(origins = "*")

@RestController
public class AdminEmployeeController {

    private final EmployeeService employeeService;
    private final WorkingShiftService workingShiftService;

    public AdminEmployeeController(EmployeeService employeeService, WorkingShiftService workingShiftService) {
        this.employeeService = employeeService;
        this.workingShiftService = workingShiftService;
    }

//    // Vi bruger ResponseEntity for at kommunikere med klienten med server response codes
//    @GetMapping("/employees/display")
//    public ResponseEntity<List<Employee>> getAllEmployees() {
//        List<Employee> employee = employeeService.getAllEmployees();
//
//        if (employee == null) {
//            return ResponseEntity.notFound().build();
//        } else {
//            return ResponseEntity.ok(employee);
//        }
//    }

    @GetMapping("/api/employees")
    @ResponseBody
    public List<EmployeeDTO> getAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/employees/display")
    public String showAllEmployees() {
        return "employee-overview";
    }

    @GetMapping("/working-shift/display")
    public ResponseEntity<List<WorkingShiftDTO>> showAllWorkingShifts() {
        List<WorkingShiftDTO> workingShiftList = workingShiftService.getAllWorkingShifts();

        if (workingShiftList == null) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(workingShiftList);
        }
    }
}   
