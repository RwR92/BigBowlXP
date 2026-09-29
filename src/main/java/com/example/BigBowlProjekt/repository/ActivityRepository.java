package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ActivityRepository extends JpaRepository<Activity, Long> {
}
