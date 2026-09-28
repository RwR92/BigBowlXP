package com.example.BigBowlProjekt.init;

import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.repository.WorkingShiftRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Component
public class WorkingShiftInit implements CommandLineRunner {

    private final WorkingShiftRepository workingShiftRepository;

    public WorkingShiftInit(WorkingShiftRepository workingShiftRepository) {
        this.workingShiftRepository = workingShiftRepository;
    }

    @Override
    public void run(String... args) {

        if (workingShiftRepository.count() == 0) {

            WorkingShift shift1 = new WorkingShift(
                    LocalDate.of(2026, 9, 28),
                    LocalTime.of(8, 0),
                    LocalTime.of(16, 0)
            );

            WorkingShift shift2 = new WorkingShift(
                    LocalDate.of(2026, 9, 28),
                    LocalTime.of(16, 0),
                    LocalTime.of(22, 0)
            );

            WorkingShift shift3 = new WorkingShift(
                    LocalDate.of(2026, 9, 29),
                    LocalTime.of(8, 0),
                    LocalTime.of(16, 0)
            );

            WorkingShift shift4 = new WorkingShift(
                    LocalDate.of(2026, 9, 30),
                    LocalTime.of(12, 0),
                    LocalTime.of(20, 0)
            );

            WorkingShift shift5 = new WorkingShift(
                    LocalDate.of(2026, 10, 1),
                    LocalTime.of(14, 0),
                    LocalTime.of(22, 0)
            );

            workingShiftRepository.save(shift1);
            workingShiftRepository.save(shift2);
            workingShiftRepository.save(shift3);
            workingShiftRepository.save(shift4);
            workingShiftRepository.save(shift5);
        }
    }
}