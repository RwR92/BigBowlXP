package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import com.example.BigBowlProjekt.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee getEmployeesByEmployeeId(Long employeeId) {
        return EmployeeRepository.getEmployeesByEmployeeId();
    }

    public List<Employee> getAllEmployees() {
        return EmployeeRepository.getAllEmployees();
    }

}
