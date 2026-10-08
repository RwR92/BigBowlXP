package com.example.BigBowlProjekt.init;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.EmployeeRepository;
import com.example.BigBowlProjekt.repository.SaleRepository;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class TestDataInit implements CommandLineRunner {

    private final WorkingShiftRepository workingShiftRepository;
    private final EmployeeRepository employeeRepository;
    private final SaleRepository saleRepository;

    public TestDataInit(
            WorkingShiftRepository workingShiftRepository,
            EmployeeRepository employeeRepository, SaleRepository saleRepository) {

        this.workingShiftRepository = workingShiftRepository;
        this.employeeRepository = employeeRepository;
        this.saleRepository = saleRepository;
    }

    @Override
    public void run(String... args) {

        // EMPLOYEES
        if (employeeRepository.count() == 0 || workingShiftRepository.count() == 0) {

            Employee employee1 = new Employee(
                    "Anders",
                    "Jensen",
                    "12345678",
                    "Manager"
            );

            Employee employee2 = new Employee(
                    "Emma",
                    "Nielsen",
                    "87654321",
                    "Employee"
            );

            Employee employee3 = new Employee(
                    "Oliver",
                    "Hansen",
                    "22446688",
                    "Employee"
            );

            Employee employee4 = new Employee(
                    "Sofie",
                    "Larsen",
                    "55667788",
                    "Employee"
            );

            Employee employee5 = new Employee(
                    "Lucas",
                    "Pedersen",
                    "11223344",
                    "Manager"
            );

            employeeRepository.save(employee1);
            employeeRepository.save(employee2);
            employeeRepository.save(employee3);
            employeeRepository.save(employee4);
            employeeRepository.save(employee5);

            WorkingShift shift1 = new WorkingShift(
                    LocalDate.of(2026, 9, 28),
                    LocalTime.of(8, 0),
                    LocalTime.of(16, 0),
                    employee1
            );

            WorkingShift shift2 = new WorkingShift(
                    LocalDate.of(2026, 9, 28),
                    LocalTime.of(16, 0),
                    LocalTime.of(22, 0),
                    employee2
            );

            WorkingShift shift3 = new WorkingShift(
                    LocalDate.of(2026, 9, 29),
                    LocalTime.of(8, 0),
                    LocalTime.of(16, 0),
                    employee3
            );

            WorkingShift shift4 = new WorkingShift(
                    LocalDate.of(2026, 9, 30),
                    LocalTime.of(12, 0),
                    LocalTime.of(20, 0),
                    employee4
            );

            WorkingShift shift5 = new WorkingShift(
                    LocalDate.of(2026, 10, 1),
                    LocalTime.of(14, 0),
                    LocalTime.of(22, 0),
                    employee5
            );

            workingShiftRepository.save(shift1);
            workingShiftRepository.save(shift2);
            workingShiftRepository.save(shift3);
            workingShiftRepository.save(shift4);
            workingShiftRepository.save(shift5);
        }

    }
}