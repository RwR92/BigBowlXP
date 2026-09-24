package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepsitory;
import com.example.BigBowlProjekt.service.EmployeeService;
import com.example.BigBowlProjekt.service.WorkingShiftService;
import java.util.List;

@RestController
public class AdminEmployeeController {

    private final EmployeeService employeeService;

    public AdminEmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
    

    @GetMapping("/employees/display")
    public List<Employee> showAllEmployees() {
        return employeeService.getAllEmployees();
    }

    @GetMapping("/working-shift/display")
    public List<WorkingShift> showAllWorkingShifts() {
        return WorkingShiftService.getAllWorkingShifts();
    }

}   
