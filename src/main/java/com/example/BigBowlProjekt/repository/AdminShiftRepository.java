package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminShiftRepository extends JpaRepository<WorkingShift, Long> {
}
