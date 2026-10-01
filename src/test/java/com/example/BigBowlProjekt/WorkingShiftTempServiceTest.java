package com.example.BigBowlProjekt;

import com.example.BigBowlProjekt.model.WorkingShift;
import com.example.BigBowlProjekt.service.WorkingShiftTempService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class WorkingShiftTempServiceTest {

    @Mock
    WorkingShift workingShift;

    WorkingShiftTempService service;

    @BeforeEach
    void setUp() {
        service = new WorkingShiftTempService(null, null);
    }

    @Test
    void overlaps_returnsTrueWhenShiftsOverlap() {
        when(workingShift.getStartTime()).thenReturn(LocalTime.of(12, 0));
        when(workingShift.getEndTime()).thenReturn(LocalTime.of(16, 0));

        boolean result = service.overlaps(
                LocalTime.of(14, 0),
                LocalTime.of(18, 0),
                workingShift
        );

        assertTrue(result);
    }
}
