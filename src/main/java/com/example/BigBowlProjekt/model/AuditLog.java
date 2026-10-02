package com.example.BigBowlProjekt.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private String user;

    private String action;

    private LocalDateTime timeStamp;

    private String description;

    protected AuditLog(){}

    public AuditLog (String user, String action, LocalDateTime timeStamp, String description) {
        this.user = user;
        this.action = action;
        this.timeStamp = timeStamp;
        this.description = description;
    }

    public Long getId() {
        return id;
    }

    public String getUser() {
        return user;
    }

    public String getAction() {
        return action;
    }

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getDescription() {
        return description;
    }
}
