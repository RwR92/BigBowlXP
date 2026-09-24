package com.example.BigBowlProjekt.model;


import jakarta.persistence.*;
import org.springframework.web.bind.annotation.RequestParam;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private ReservationType type;

    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @ManyToMany
    @JoinTable(
            name = "reservation_lane",
            joinColumns = @JoinColumn(name = "reservation_id"),
            inverseJoinColumns = @JoinColumn(name = "lane_id")
    )

    private List<Lane> lanes = new ArrayList<>();
    private Integer guests;

 //   @ManyToMany
   // @JoinColumn(name = "booking_id")
   // private Booking booking;

    public  Reservation() {

}
    public Reservation(ReservationType type, LocalDateTime startTime, LocalDateTime endTime,
                       List<Lane> lanes, Integer guests) {
        this.type = type;
        this.startTime = startTime;
        this.endTime = endTime;
        this.lanes = lanes;
        this.guests = guests;
    }

 //   public Booking getBooking() {
 //       return booking;
 //   public void setBooking(Booking booking) {
//this.booking = booking;

    public Integer getGuests() {
        return guests;
    }
    public void setGuests(Integer guests) {
        this.guests = guests;
    }
    public List<Lane> getLanes() {
        return lanes;
    }
    public void setLanes(List<Lane> lanes) {
        this.lanes = lanes;
    }
    public LocalDateTime getEndTime() {
        return endTime;
    }
    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }
    public LocalDateTime getStartTime() {
        return startTime;
    }
    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }
    public ReservationType getType() {
        return type;
    }
    public void setType(ReservationType type) {
        this.type = type;
    }
    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
    }
}

