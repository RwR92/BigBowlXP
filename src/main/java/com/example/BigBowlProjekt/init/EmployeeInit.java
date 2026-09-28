package com.example.BigBowlProjekt.init;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class EmployeeInit implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;

    public EmployeeInit(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String... args) {

        if (employeeRepository.count() == 0) {

            Employee employee1 = new Employee(
                    null,
                    "Anders",
                    "Andersen",
                    "12345678",
                    "Employee"
            );

            Employee employee2 = new Employee(
                    null,
                    "Mikkel",
                    "Jensen",
                    "87654321",
                    "Employee"
            );

            Employee employee3 = new Employee(
                    null,
                    "Sofie",
                    "Nielsen",
                    "11223344",
                    "Manager"
            );

            Employee employee4 = new Employee(
                    null,
                    "Emma",
                    "Hansen",
                    "44332211",
                    "Employee"
            );

            Employee employee5 = new Employee(
                    null,
                    "Oliver",
                    "Larsen",
                    "55667788",
                    "Employee"
            );

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);
            employeeRepository.save(employee3);
            employeeRepository.save(employee4);
            employeeRepository.save(employee5);
        }
    }
}
