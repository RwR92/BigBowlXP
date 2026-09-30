package com.example.BigBowlProjekt.model;

import com.fasterxml.jackson.annotation.JsonPropertyOrder;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
@JsonPropertyOrder({"id", "date", "startTime", "endTime", "employee"})
public class WorkingShift {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long workingShiftId;

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    @ManyToOne
    @JoinColumn(name = "employee_id")
    private Employee employee;

    public WorkingShift(LocalDate date, LocalTime startTime, LocalTime endTime, Employee employee) {
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.employee = employee;
    }

    public WorkingShift() {
    }

    public Long getWorkingShiftId() {
        return workingShiftId;
    }

    public void setWorkingShiftId(Long id) {
        this.workingShiftId = id;
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

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
