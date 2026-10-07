package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.ActivityDTO;
import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.model.Activity;
import com.example.BigBowlProjekt.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin/reservation")
public class AdminReservationController {

    private final ReservationService reservationService;

    public AdminReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }
    @GetMapping
    public List<ReservationDTO> getAllReservations() {
        return reservationService.getAllReservations();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ReservationDTO>getReservationById(@PathVariable Long id){
        Optional<ReservationDTO> reservation = reservationService.getReservationById(id);
        if(reservation.isPresent()){
            return ResponseEntity.ok(reservation.get());
        }
        return ResponseEntity.notFound().build();
    }
    @GetMapping("/day-overview")
    public List<ReservationDTO> getDayOverview(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate date) {
        return reservationService.getDayOverview(date);

    }
        @PostMapping
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO) {
        return reservationService.createReservation(reservationDTO);
    }

        @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id){
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
        }

    }



