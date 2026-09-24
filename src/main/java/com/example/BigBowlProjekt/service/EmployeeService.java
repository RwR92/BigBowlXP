package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepsitory;
import com.example.BigBowlProjekt.service.EmployeeService;
import java.util.List;

public class EmployeeService {

    public Employee getEmployeeByEmployeeId(Long employeeId) {
        return EmployeeRepsitory.getAllEmployeesByEmployeeId(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return EmployeeRepsitory.getAllEmployees();
    }
    
}
