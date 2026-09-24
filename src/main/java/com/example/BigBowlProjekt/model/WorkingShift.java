package com.example.BigBowlProjekt.model;

import java.time.LocalDate;
import java.time.LocalTime;

public class WorkingShift {

    private Long WorkingShiftId;
    private Long employeeId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;


    // Read
    public WorkingShift(Long workingShiftId, Long employeeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.WorkingShiftId = workingShiftId;
        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    } 
    // Create
    public WorkingShift(Long employeeId, LocalDate date, LocalTime startTime, LocalTime endTime) {
        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    } 


    public Long getWorkingShiftId() {
        return WorkingShiftId;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}
