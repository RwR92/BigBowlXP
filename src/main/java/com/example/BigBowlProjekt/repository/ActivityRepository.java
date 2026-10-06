package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Activity;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {

    List<Activity> findByStartTimeLessThanAndEndTimeGreaterThan(
            LocalDateTime to,
            LocalDateTime from
    );

}
