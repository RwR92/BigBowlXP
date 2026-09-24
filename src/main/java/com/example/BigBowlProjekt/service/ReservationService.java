package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.model.Customer;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.Reservation;
import com.example.BigBowlProjekt.model.ReservationType;
import com.example.BigBowlProjekt.repository.CustomerRepository;
import com.example.BigBowlProjekt.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


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
            reservationDTOs.add()
        }
    }

    @Transactional
    public Reservation createReservation(ReservationType reservationType, List<long> laneIds,
                                         LocalDateTime startTime, int hours) {
        validateLaneCount(laneIds);
        validateDuration(hours);

        LocalDateTime endtime = startTime.plusHours(hours);

        List<Lane> lanes = laneRepository.findAllByID(laneIds);
        if (lanes.size() != laneIds.size()) {
            throw new IllegalArgumentException("En eller flere baner findes ikke.");

        }
        private void validateLaneCount(List<Long> laneIds){
            if (laneIds == null || laneIds.size() < MIN_LANES || laneIds.size() > MAX_LANES) {
                throw new IllegalArgumentException("Du kan booke mellem 1 eller 4 baner. ");
            }
        }
    }
}
