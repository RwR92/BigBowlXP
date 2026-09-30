package com.example.BigBowlProjekt.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonPropertyOrder({"id", "date", "startTime", "endTime"})
public class WorkingShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workingShiftId;

    private Long employeeId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public WorkingShift(
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime) {

        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WorkingShift(
            Long employeeId,
            LocalDate date,
            LocalTime startTime,
            LocalTime endTime) {

        this.employeeId = employeeId;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    public WorkingShift() {
    }

    public Long getWorkingShiftId() {
        return workingShiftId;
    }

    public void setWorkingShiftId(Long id) {
        this.workingShiftId = id;
    }

    public Long getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(Long employeeId) {
        this.employeeId = employeeId;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }
}
