    package com.example.BigBowlProjekt.service;

    import com.example.BigBowlProjekt.dto.ActivityDTO;
    import com.example.BigBowlProjekt.dto.ReservationDTO;
    import com.example.BigBowlProjekt.mapper.ReservationMapper;
    import com.example.BigBowlProjekt.model.Activity;
    import com.example.BigBowlProjekt.model.Reservation;
    import com.example.BigBowlProjekt.repository.ActivityRepository;
    import com.example.BigBowlProjekt.repository.LaneRepository;
    import com.example.BigBowlProjekt.repository.ReservationRepository;
    import org.springframework.stereotype.Service;

    import java.time.LocalDate;
    import java.util.ArrayList;
    import java.util.List;
    import java.util.Optional;

    @Service
    public class ReservationService {
        private final ActivityRepository activityRepository;
        private final LaneRepository laneRepository;
        private final ReservationRepository reservationRepository;
        private final ActivityService activityService;

        public ReservationService(ActivityRepository activityRepository,
                                  LaneRepository laneRepository, ReservationRepository reservationRepository, ActivityService activityService) {
            this.activityRepository = activityRepository;
            this.laneRepository = laneRepository;
            this.reservationRepository = reservationRepository;
            this.activityService = activityService;
        }

        public List<ReservationDTO> getAllReservations() {
            List<Reservation> reservations = reservationRepository.findAll();
            List<ReservationDTO> reservationDTOS = new ArrayList<>();

            for (Reservation reservation : reservations) {
                reservationDTOS.add(ReservationMapper.toDTO(reservation));
            }

            return reservationDTOS;
        }

        public Optional<ReservationDTO> getReservationById(Long id) {
            return reservationRepository.findById(id).map(ReservationMapper::toDTO);
        }

        public ReservationDTO createReservation(ReservationDTO dto) {
            List<Activity> activities = new ArrayList<>();

            for (ActivityDTO activityDTO : dto.activities()) {
                Activity activity = activityService.buildValidActivity(activityDTO);
                activities.add(activity);
            }

            Reservation reservation = new Reservation(
                    null,
                    dto.name(),
                    activities
            );

            for (Activity activity : activities) {
                activity.setReservation(reservation);
            }

            Reservation saved = reservationRepository.save(reservation);

            return ReservationMapper.toDTO(saved);
        }

        public void deleteReservation(Long id) {
            reservationRepository.deleteById(id);
        }
        public List<ReservationDTO> getDayOverview(LocalDate date) {
            List<ReservationDTO> result = new ArrayList<>();

            for (Reservation reservation : reservationRepository.findAll()) {

                boolean hasActivityOnDate = false;

                for (Activity activity : reservation.getActivities()) {
                    if (activity.getStartTime().toLocalDate().equals(date)) {
                        hasActivityOnDate = true;
                        break;
                    }
                }

                if (hasActivityOnDate) {
                    result.add(ReservationMapper.toDTO(reservation));
                }
            }

            return result;
        }
    }

