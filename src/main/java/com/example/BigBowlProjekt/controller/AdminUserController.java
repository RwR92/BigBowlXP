package com.example.BigBowlProjekt.controller;


import com.example.BigBowlProjekt.dto.EmployeeDTO;
import com.example.BigBowlProjekt.mapper.EmployeeMapper;
import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.service.EmployeeService;
import com.example.BigBowlProjekt.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin/user")

public class AdminUserController {
    private final UserService userService;
    private final EmployeeService employeeService;

    public AdminUserController(UserService userService, EmployeeService employeeService) {
        this.userService = userService;
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<EmployeeDTO> getAllUsers(){
        return employeeService.getAllEmployees();
    }

    @PostMapping
    public EmployeeDTO createUser(@RequestBody Employee employee) {
        Employee created = userService.createUser(employee);
        return EmployeeMapper.toDTO(created);
    }
}
