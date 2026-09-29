package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.dto.EmployeeDTO;
import com.example.BigBowlProjekt.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeDTO, Long> {

    //der skal kun laves metoder for ikke-standart java metoder

    List<EmployeeDTO> getEmployeeByEmployeeId(Long employeeId);

    List<EmployeeDTO> getEmployeeByRole(String role);
}
