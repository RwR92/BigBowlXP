package com.example.BigBowlProjekt;

import com.example.BigBowlProjekt.dto.LaneSummaryDTO;
import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.model.Lane;
import com.example.BigBowlProjekt.model.LaneType;
import com.example.BigBowlProjekt.model.Reservation;
import com.example.BigBowlProjekt.model.ReservationType;
import com.example.BigBowlProjekt.repository.CustomerRepository;
import com.example.BigBowlProjekt.repository.LaneRepository;
import com.example.BigBowlProjekt.repository.ReservationRepository;
import com.example.BigBowlProjekt.service.ReservationService;
import com.mysql.cj.xdevapi.AddStatement;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import com.mysql.cj.xdevapi.AddStatement;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.junit.jupiter.api.Assertions.*;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
class ReservationServiceTest {

    @Mock
    private ReservationRepository reservationRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private LaneRepository laneRepository;

    @InjectMocks
    private ReservationService reservationService;

    private Lane lane1;
    private Lane lane2;
    private Lane lane3;
    private Lane lane4;
    private Lane lane5;

    @BeforeEach
    void setUp() {
        lane1 = new Lane(1, LaneType.BOWLING, false);
        lane1.setId(1L);
        lane2 = new Lane(2, LaneType.BOWLING, false);
        lane2.setId(2L);
        lane3 = new Lane(3, LaneType.BOWLING, false);
        lane3.setId(3L);
        lane4 = new Lane(4, LaneType.BOWLING, false);
        lane4.setId(4L);
        lane5 = new Lane(5, LaneType.BOWLING, false);
        lane5.setId(5L);
    }

    private ReservationDTO buildDto(List<Lane> lanes, LocalDateTime startDate, LocalDateTime endDate) {
        List<LaneSummaryDTO> laneSummeries = new ArrayList<>();
        for (Lane lane : lanes) {
            laneSummeries.add(new LaneSummaryDTO(lane.getId(), lane.getLaneNumber(), lane.getType()));
        }
        return new ReservationDTO(null, ReservationType.BOWLING, startDate, endDate, laneSummeries, null);
    }

    // hjælpe metode til test
    private LocalDateTime nextSaturday() {
        LocalDateTime now = LocalDateTime.now();
        while (now.getDayOfWeek() != DayOfWeek.SATURDAY) {
            now = now.plusDays(1);
        }
        return now;
    }

    // Test 1: 5 Baner skal afvise
    @Test
    void createReservation_witch5Lanes_catachFail() {
        List<Lane> fiveLanes = List.of(lane1, lane2, lane3, lane4, lane5);
        LocalDateTime start = nextSaturday().withHour(18).withMinute(0);
        LocalDateTime end = start.plusHours(1);

        ReservationDTO dto = buildDto(fiveLanes, start, end);

        try {
            reservationService.createReservation(dto);
            fail("Forventede en IllegalArgumentException, men der blev ikke kastet nogen.");
        } catch (IllegalArgumentException exception) {
            assertEquals("Du kan booke mellem 1 og 4 baner.", exception.getMessage());
        }
    }
    // Test 2: 3 timer skal afvise
    @Test
    void createReservation_with3Hours_CatchFail(){
        List<Lane> oneLanes = List.of(lane1);
        LocalDateTime start = nextSaturday().withHour(18).withMinute(0);
        LocalDateTime end = start.plusHours(3);

        ReservationDTO dto = buildDto(oneLanes, start, end);
        try {
            reservationService.createReservation(dto);
            fail("Forventede en IllegalArgumentException, men der blev ikke kastet nogen.");
        }catch (IllegalArgumentException exception){
            assertEquals("Du kan booke i 1 eller 2 timer.", exception.getMessage());
        }
    }

}
