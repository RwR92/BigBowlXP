package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.LaneSummaryDTO;
import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.mapper.ReservationMapper;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.Reservation;
import com.example.BigBowlProjekt.repository.CustomerRepository;
import com.example.BigBowlProjekt.repository.LaneRepository;
import com.example.BigBowlProjekt.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private static final int MIN_LANES = 1;
    private static final int MAX_LANES = 4;
    private static final int MIN_HOURS = 1;
    private static final int MAX_HOURS = 2;

    private static final int CLUB_MAX_LANES = 14;
    private static final LocalTime CLUB_START_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLUB_END_TIME = LocalTime.of(17, 0);

    private final CustomerRepository customerRepository;
    private final ReservationRepository reservationRepository;
    private final LaneRepository laneRepository;

    public ReservationService(CustomerRepository customerRepository, ReservationRepository reservationRepository,
                              LaneRepository laneRepository) {
        this.customerRepository = customerRepository;
        this.reservationRepository = reservationRepository;
        this.laneRepository = laneRepository;
    }

    public List<ReservationDTO> getAllReservations() {
        List<Reservation> reservations = reservationRepository.findAll();
        List<ReservationDTO> reservationDTOs = new ArrayList<>();

        for (Reservation reservation : reservations) {
            reservationDTOs.add(ReservationMapper.toDTO(reservation));
        }

        return reservationDTOs;
    }

    public Optional<ReservationDTO> getReservationById(Long id) {
        return reservationRepository.findById(id).map(ReservationMapper::toDTO);
    }

    @Transactional
    public ReservationDTO createReservation(ReservationDTO dto) {
        List<Long> laneIds = extractLaneIds(dto.lanes());

        validateLaneCount(laneIds);
        validateDuration(dto.startTime(), dto.endTime());

        List<Lane> lanes = laneRepository.findAllById(laneIds);
        if (lanes.size() != laneIds.size()) {
            throw new IllegalArgumentException("En eller flere baner findes ikke.");
        }

        validateNoOverlap(laneIds, dto.startTime(), dto.endTime());

        Reservation reservation = new Reservation(dto.type(), dto.startTime(), dto.endTime(), lanes, dto.guests());
        Reservation saved = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(saved);
    }

    public void deleteReservation(Long id) {
        reservationRepository.deleteById(id);
    }

    private List<Long> extractLaneIds(List<LaneSummaryDTO> laneSummaries) {
        List<Long> ids = new ArrayList<>();
        for (LaneSummaryDTO summary : laneSummaries) {
            ids.add(summary.id());
        }
        return ids;
    }

    private void validateLaneCount(List<Long> laneIds) {
        if (laneIds == null || laneIds.size() < MIN_LANES || laneIds.size() > MAX_LANES) {
            throw new IllegalArgumentException("Du kan booke mellem 1 og 4 baner.");
        }
    }

    private void validateDuration(LocalDateTime startTime, LocalDateTime endTime) {
        if (startTime == null || endTime == null || !endTime.isAfter(startTime)) {
            throw new IllegalArgumentException("Sluttid skal ligge efter starttid.");
        }

        long minutes = Duration.between(startTime, endTime).toMinutes();
        if (minutes != MIN_HOURS * 60 && minutes != MAX_HOURS * 60) {
            throw new IllegalArgumentException("Du kan booke i 1 eller 2 timer.");
        }
    }

    private void validateNoOverlap(List<Long> laneIds, LocalDateTime startTime, LocalDateTime endTime) {
        List<Reservation> allReservations = reservationRepository.findAll();

        for (Reservation existing : allReservations) {

            // Tester om tiden overlapper
            if (!existing.overlaps(startTime, endTime)) {
                continue;
            }

            // Bruger den eksisterende reservation en af de samme baner?
            for (Lane lane : existing.getLanes()) {
                if (laneIds.contains(lane.getId())) {
                    throw new IllegalArgumentException("Bane " + lane.getLaneNumber()
                            + " er allerede booket i tidsrummet.");
                }
            }
        }
    }
}