package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaneRepository extends JpaRepository<Lane, Long> {
    List<Lane> findByType(LaneType type);
}