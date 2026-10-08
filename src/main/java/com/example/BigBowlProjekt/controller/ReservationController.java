package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.service.AuditService;
import com.example.BigBowlProjekt.service.ReservationService;
import jakarta.servlet.http.HttpSession;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {
    private final ReservationService reservationService;
    private final AuditService auditService;

    public ReservationController(ReservationService reservationService, AuditService auditService) {
        this.reservationService = reservationService;
        this.auditService = auditService;
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
    public ReservationDTO createReservation(@RequestBody ReservationDTO reservationDTO, HttpSession session) {
        auditService.log(
                auditService.userGrabber(session),
                "Opret",
                "Oprettet en reservation bestående af " +
                        reservationDTO.activities().stream()
                                .map(activityDTO -> activityDTO.type().name().toLowerCase())
                                .collect(Collectors.joining(", "))
        );
        return reservationService.createReservation(reservationDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable Long id, HttpSession session) {
        Optional<ReservationDTO> byId = reservationService.getReservationById(id);

        if (byId.isPresent()) {
            auditService.log(
                    auditService.userGrabber(session),
                    "Slet","Slettede reservation for " + byId.get().name() +
                            " med aktiviterne " + byId.get().activities().stream()
                            .map(activityDTO -> activityDTO.type().name().toLowerCase())
                            .collect(Collectors.joining(", "))
            );
            reservationService.deleteReservation(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
