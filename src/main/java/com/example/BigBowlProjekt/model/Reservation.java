package com.example.BigBowlProjekt.model;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL)
    private List<Activity> activities = new ArrayList<>();

    // constructors, getters, setters

    public Reservation() {};

    public Reservation(Long id, String name, List<Activity> activities) {
        this.id = id;
        this.name = name;
        this.activities = activities;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Activity> getActivities() {
        return activities;
    }

    public void setActivities(List<Activity> activities) {
        this.activities = activities;
    }
}