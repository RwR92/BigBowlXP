package com.example.BigBowlProjekt.repository

import com.example.BigBowlProjekt.model.Reservation
import org.springframework.data.jpa.repository.JpaRepository


interface ReservationRepository : JpaRepository<Reservation, Long> {

}
