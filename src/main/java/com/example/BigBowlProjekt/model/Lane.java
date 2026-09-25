package com.example.BigBowlProjekt.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Lane {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int laneNumber;
    @Enumerated(EnumType.STRING)
    private LaneType type;
    private boolean childFriendly;


    // Default constructor required by JPA
    public Lane() {}

    // Constructor with fields
    public Lane(int laneNumber, LaneType type, boolean childFriendly) {
        this.laneNumber = laneNumber;
        this.type = type;
        this.childFriendly = childFriendly;


    }
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public int getLaneNumber() {return laneNumber;}
    public void setLaneNumber(int laneNumber) {this.laneNumber = laneNumber;    }
    public LaneType getType() {return type;}
    public void setType(LaneType type) {this.type = type;}
    public boolean isChildFriendly() {return childFriendly;}
    public void setChildFriendly(boolean childFriendly) {this.childFriendly = childFriendly;}
}
