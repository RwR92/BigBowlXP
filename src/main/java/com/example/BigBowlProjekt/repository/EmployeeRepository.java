package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    List<Employee> getEmployeesByEmployeeId(Long employeeId);

    List<Employee> getAllEmployees();
}
