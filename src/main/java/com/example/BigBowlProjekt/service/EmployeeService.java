package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import com.example.BigBowlProjekt.service.EmployeeService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    public Employee getAllEmployeesByEmployeeId(Long employeeId) {
        return EmployeeRepository.getAllEmployeesByEmployeeId(employee);
    }

    public List<Employee> getAllEmployees() {
        return EmployeeRepository.getAllEmployees(Employee);
    }

}
