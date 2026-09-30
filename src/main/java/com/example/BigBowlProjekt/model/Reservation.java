package com.example.BigBowlProjekt.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String reservationNumber;
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "reservation", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Activity> activities = new ArrayList<>();

    public Reservation() {}

    public Reservation(Customer customer) {
        this.customer = customer;
        this.createdAt = LocalDateTime.now();
    }

    public void addActivity(Activity activity) {
        activities.add(activity);
        activity.setReservation(this);
    }

    public void removeActivity(Activity activity) {
        activities.remove(activity);
        activity.setReservation(null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReservationNumber() {
        return reservationNumber;
    }

    public void setReservationNumber(String reservationNumber) {
        this.reservationNumber = reservationNumber;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Activity> getActivities() {
        return activities;
    }
}