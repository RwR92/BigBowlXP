package com.example.BigBowlProjekt.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record ShiftFormDTO(
        Long workingShiftId,
        Long employeeId,
        LocalDate date,
        LocalTime startTime,
        LocalTime endTime
) {
}
