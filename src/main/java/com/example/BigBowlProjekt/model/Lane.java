package com.example.BigBowlProjekt.model;

import jakarta.persistence.*;

@Entity
public class Lane {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private int laneNumber;
    @Enumerated(EnumType.STRING)
    private LaneType type;
    private Boolean childFriendly;
    private Boolean isOpen=true;


    public Lane() {}

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
    public boolean getIsOpen(){return isOpen;}
    public void setIsOpen(boolean isOpen){this.isOpen = isOpen;}
}
