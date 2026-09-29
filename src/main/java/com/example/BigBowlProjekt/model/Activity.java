package com.example.BigBowlProjekt.model;


import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Activity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ActivityType type;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name = "activity_lane",
            joinColumns = @JoinColumn(name = "activity_id"),
            inverseJoinColumns = @JoinColumn(name = "lane_id")

    )

    private List<Lane> lanes = new ArrayList<>();
    private Integer guests;
    @ManyToOne
    @JoinColumn (name = "reservation_id")
    private Reservation reservation;

    public Activity() {

}
    public Activity(ActivityType type, LocalDateTime startTime, LocalDateTime endTime,
                    List<Lane> lanes, Integer guests) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lanes = lanes;
        this.guests = guests;
    }
    public boolean overlaps(LocalDateTime otherStart, LocalDateTime otherEnd) {
        return startTime.isBefore(otherEnd) && endTime.isAfter(otherStart);
    }

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public ActivityType getType() {return type;}
    public void setType(ActivityType type) {this.type = type;}
    public LocalDateTime getStartTime() {return startTime;}
    public void setStartTime(LocalDateTime startTime) {this.startTime = startTime;}
    public LocalDateTime getEndTime() {return endTime;}
    public void setEndTime(LocalDateTime endTime) {this.endTime = endTime;}
    public List<Lane> getLanes() {return lanes;}
    public void setLanes(List<Lane> lanes) {this.lanes = lanes;}
    public Integer getGuests() {return guests;}
    public void setGuests(Integer guests) {this.guests = guests;}
    public Reservation getReservation() {return reservation;
    }public void setReservation(Reservation reservation) { this.reservation = reservation;
    }}

