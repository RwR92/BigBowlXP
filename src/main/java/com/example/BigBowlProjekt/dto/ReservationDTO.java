package com.example.BigBowlProjekt.dto;

import java.time.LocalDateTime;
import java.util.List;

public class ReservationDTO {

    Long id;
    String reservationNumber;
    LocalDateTime createdAt;
    CustomerDTO customer;
    List<ActivityDTO> activities;

}
