package com.example.BigBowlProjekt.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkingShiftDTO(
        Long id,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        EmployeeDTO employeeDTO
) {
}
