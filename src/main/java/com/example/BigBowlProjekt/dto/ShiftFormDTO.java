package com.example.BigBowlProjekt.dto;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public record ShiftFormDTO(
        Long id,
        LocalDate startTime,
        LocalDate endTime,
        LocalDate date;
) {
}
