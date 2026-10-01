package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.ActivityDTO;
import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.mapper.ReservationMapper;
import com.example.BigBowlProjekt.model.Activity;
import com.example.BigBowlProjekt.model.Customer;
import com.example.BigBowlProjekt.model.Reservation;
import com.example.BigBowlProjekt.repository.CustomerRepository;
import com.example.BigBowlProjekt.repository.ReservationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final CustomerRepository customerRepository;
    private final ActivityService activityService;

    public ReservationService(ReservationRepository reservationRepository,
                              CustomerRepository customerRepository,
                              ActivityService activityService) {
        this.reservationRepository = reservationRepository;
        this.customerRepository = customerRepository;
        this.activityService = activityService;
    }

    public List<ReservationDTO> getAllReservations() {
        List<ReservationDTO> result = new ArrayList<>();
        for (Reservation reservation : reservationRepository.findAll()) {
            result.add(ReservationMapper.toDTO(reservation));
        }
        return result;

    }

    public Optional<ReservationDTO> getReservationById(Long id) {
        return reservationRepository.findById(id).map(ReservationMapper::toDTO);
    }

    @Transactional
    public ReservationDTO createReservation(Long customerId, List<ActivityDTO> activityLines) {

        Customer customer = customerRepository.findById(customerId)
                .orElseThrow(() -> new IllegalArgumentException("Kunde findes ikke."));

        Reservation reservation = new Reservation(customer);
        reservation.setReservationNumber(generateReservationNumber());

        for (ActivityDTO line : activityLines) {
            Activity activity = activityService.buildValidActivity(line);
            reservation.addActivity(activity);
        }

        Reservation saved = reservationRepository.save(reservation);
        return ReservationMapper.toDTO(saved);
    }
    private String generateReservationNumber(){
        return "BIGBOW_"+ UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}

