package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
