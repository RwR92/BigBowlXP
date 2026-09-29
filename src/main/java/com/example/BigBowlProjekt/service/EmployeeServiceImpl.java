package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.EmployeeDTO;
import com.example.BigBowlProjekt.mapper.EmployeeMapper;
import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;

    public EmployeeServiceImpl(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public List<EmployeeDTO> getAllEmployees() {
        List<Employee> employees = employeeRepository.findAll();

        List<EmployeeDTO> employeeDTOList = new ArrayList<>();

        for (Employee emp : employees) {

             EmployeeDTO empDTO = EmployeeMapper.toDTO(emp);

             employeeDTOList.add(empDTO);
        }

        return employeeDTOList;
    }

    @Override
    public Optional<Employee> getEmployeeByRole() {
        return Optional.empty();
    }
}
