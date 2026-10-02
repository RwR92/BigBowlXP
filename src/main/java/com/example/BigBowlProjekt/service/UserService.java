package com.example.BigBowlProjekt.service;


import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final EmployeeRepository employeeRepository;

    public UserService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public Employee createUser(Employee employee) {
        if (employeeRepository.existsByFirstNameAndLastName(employee.getFirstName(), employee.getLastName())) {
            throw new IllegalArgumentException("Employee with this name already exists");
        }
        return employeeRepository.save(employee);
    }

    public List<Employee> getAllUsers() {
        return employeeRepository.findAll();
    }
}


