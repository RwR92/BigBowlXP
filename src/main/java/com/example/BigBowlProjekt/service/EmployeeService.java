package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import com.example.BigBowlProjekt.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public List<Employee> getEmployeesByEmployeeId(Long employeeId) {
        return employeeRepository.getEmployeesByEmployeeId(employeeId);
    }

    public List<Employee> getAllEmployees() {
        return employeeRepository.getAllEmployees();
    }

}
