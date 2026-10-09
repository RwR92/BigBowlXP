package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByStartTimeLessThanAndEndTimeGreaterThan(
            LocalDateTime to,
            LocalDateTime from
    );

}
