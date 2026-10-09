package com.example.BigBowlProjekt;

import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.dto.UserTypeDTO;
import com.example.BigBowlProjekt.model.AuditLog;
import com.example.BigBowlProjekt.repository.AuditLogRepository;
import com.example.BigBowlProjekt.service.AuditService;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.mockito.ArgumentCaptor;
import org.springframework.mock.web.MockHttpSession;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

class AuditServiceTest {
    @ParameterizedTest
    @CsvSource({"POST, Opprettet", "PUT, Opdateret", "DELETE, Slettet"})
    void reservationAuditAcceptsHttpActions(String action, String expectedAction) {
        AuditLogRepository repository = mock(AuditLogRepository.class);
        AuditService service = new AuditService(repository);
        MockHttpSession session = new MockHttpSession();
        session.setAttribute("user", new UserTypeDTO("admin"));

        service.logHandler(session, action, new ReservationDTO(1L, "Alex", List.of()));

        ArgumentCaptor<AuditLog> log = ArgumentCaptor.forClass(AuditLog.class);
        verify(repository).save(log.capture());
        assertEquals("Admin", log.getValue().getUser());
        assertEquals(expectedAction, log.getValue().getAction());
        assertEquals(expectedAction + " en reservation til Alex med aktiviteterne ",
                log.getValue().getDescription());
    }
}
