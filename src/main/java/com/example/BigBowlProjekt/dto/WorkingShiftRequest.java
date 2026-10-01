package com.example.BigBowlProjekt.dto;

import com.example.BigBowlProjekt.model.Employee;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkingShiftRequest(
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime,
        Long employeeId
) {
}
