package com.example.BigBowlProjekt.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue()
    private Long id;

    @OneToOne
    private Customer customer;

    private String action;

    private LocalDate timeStamp;

    private String description;

    protected AuditLog(){}

    public AuditLog(Customer customer, String action, LocalDate timeStamp, String description) {
        this.customer = customer;
        this.action = action;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public LocalDate getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(LocalDate timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
