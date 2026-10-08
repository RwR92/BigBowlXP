package com.example.BigBowlProjekt;

import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.service.WorkingShiftService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkingShiftServiceTest {

    @Mock
    WorkingShift workingShift;

    WorkingShiftService service;

    @BeforeEach
    void setUp() {
        service = new WorkingShiftService(null, null, null);
    }

    @Test
    void overlaps_returnsTrueWhenShiftsOverlap() {
        when(workingShift.getStartTime()).thenReturn(LocalTime.of(12, 0));
        when(workingShift.getEndTime()).thenReturn(LocalTime.of(16, 0));

        boolean result = service.overlaps(
                LocalDate.of(2026, 10, 8),
                LocalTime.of(14, 0),
                LocalTime.of(18, 0),
                workingShift
        );

        assertTrue(result);
    }
}
