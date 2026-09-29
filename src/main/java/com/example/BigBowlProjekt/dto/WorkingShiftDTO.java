package com.example.BigBowlProjekt.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public record WorkingShiftDTO(long id, LocalDate date, LocalTime startTIme, LocalTime endTime) {
}
