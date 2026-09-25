package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.CustomerDTO;
import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.model.Customer;
import com.example.BigBowlProjekt.service.ReservationService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO> getReservationById(@PathVariable Long id) {
        Optional<ReservationDTO> reservation = reservationService.getReservationById(id);
        if (reservation.isPresent()) {
            return ResponseEntity.ok(reservation.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id) {
        Optional<ReservationDTO> byId = reservationService.getReservationById(id);

        if (byId.isPresent()) {
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

