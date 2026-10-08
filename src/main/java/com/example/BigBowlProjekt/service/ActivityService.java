package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.ActivityDTO;
import com.example.BigBowlProjekt.dto.LaneSummaryDTO;
import com.example.BigBowlProjekt.mapper.ActivityMapper;
import com.example.BigBowlProjekt.model.Activity;
import com.example.BigBowlProjekt.model.ActivityType;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.repository.ActivityRepository;
import com.example.BigBowlProjekt.repository.CustomerRepository;
import com.example.BigBowlProjekt.repository.LaneRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ActivityService {

    private static final int MIN_LANES = 1;
    private static final int MAX_LANES = 4;
    private static final int MIN_HOURS = 1;
    private static final int MAX_HOURS = 2;

    private static final int CLUB_MAX_LANES = 14;
    private static final LocalTime CLUB_START_TIME = LocalTime.of(10, 0);
    private static final LocalTime CLUB_END_TIME = LocalTime.of(17, 0);

    private final CustomerRepository customerRepository;
    private final ActivityRepository activityRepository;
    private final LaneRepository laneRepository;
    private final AuditService auditService;

    public ActivityService(CustomerRepository customerRepository, ActivityRepository activityRepository,
                           LaneRepository laneRepository, AuditService auditService) {
        this.customerRepository = customerRepository;
        this.activityRepository = activityRepository;
        this.laneRepository = laneRepository;
        this.auditService = auditService;
    }

    public List<ActivityDTO> getAllActivities() {
        List<Activity> activities = activityRepository.findAll();
        List<ActivityDTO> activityDTOS = new ArrayList<>();

        for (Activity activity : activities) {
            activityDTOS.add(ActivityMapper.toDTO(activity));
        }

        return activityDTOS;
    }

    public Optional<ActivityDTO> getActivityById(Long id) {
        return activityRepository.findById(id).map(ActivityMapper::toDTO);
    }

    public Activity buildValidActivity(ActivityDTO dto) {
        if (dto.type() == ActivityType.DINING) {
            if (dto.startTime() == null || dto.endTime() == null
                    || !dto.endTime().isAfter(dto.startTime())) {
                throw new IllegalArgumentException("Sluttid skal ligge efter starttid.");
            }
            if (dto.guests() == null || dto.guests() < 1) {
                throw new IllegalArgumentException("Angiv antal gæster til spisning.");
            }
            return new Activity(dto.type(), dto.startTime(), dto.endTime(), new ArrayList<>(), dto.guests());
        }

        List<Long> laneIds = extractLaneIds(dto.lanes());

        validateLaneCount(laneIds);
        validateDuration(dto.startTime(), dto.endTime());

        List<Lane> lanes = laneRepository.findAllById(laneIds);
        if (lanes.size() != laneIds.size()) {
            throw new IllegalArgumentException("En eller flere baner findes ikke.");
        }

        validateNoOverlap(laneIds, dto.startTime(), dto.endTime());

        return new Activity(dto.type(), dto.startTime(), dto.endTime(), lanes, dto.guests());
    }

    @Transactional
    public ActivityDTO createActivity(ActivityDTO dto) {
        List<Long> laneIds = extractLaneIds(dto.lanes());

        validateLaneCount(laneIds);
        validateDuration(dto.startTime(), dto.endTime());

        List<Lane> lanes = laneRepository.findAllById(laneIds);
        if (lanes.size() != laneIds.size()) {
            throw new IllegalArgumentException("En eller flere baner findes ikke.");
        }

        validateNoOverlap(laneIds, dto.startTime(), dto.endTime());

        Activity activity = new Activity(dto.type(), dto.startTime(), dto.endTime(), lanes, dto.guests());
        Activity saved = activityRepository.save(activity);
        return ActivityMapper.toDTO(saved);
    }

    public void deleteActivity(Long id) {
        activityRepository.deleteById(id);
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
        List<Activity> allActivities = activityRepository.findAll();

        for (Activity existing : allActivities) {

            if (!existing.overlaps(startTime, endTime)) {
                continue;
            }


            for (Lane lane : existing.getLanes()) {
                if (laneIds.contains(lane.getId())) {
                    throw new IllegalArgumentException("Bane " + lane.getLaneNumber()
                            + " er allerede booket i tidsrummet.");
                }
            }
        }
    }
}