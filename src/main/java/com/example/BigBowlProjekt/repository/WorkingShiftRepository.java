package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.Employee;
import com.example.BigBowlProjekt.model.WorkingShift;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@Repository
public interface WorkingShiftRepository extends JpaRepository<WorkingShift, Long> {

    List<WorkingShift> getAllWorkingShiftsByWeek(Long workingShiftId, LocalDate date);

}
