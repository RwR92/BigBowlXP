package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminShiftRepository extends JpaRepository<WorkingShift, Long> {
}
