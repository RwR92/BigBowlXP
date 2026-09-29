package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.EmployeeDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface EmployeeService {

    List<EmployeeDTO> getAllEmployees();

    Optional<EmployeeDTO> getEmployeeByRole();

}
