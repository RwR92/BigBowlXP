package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.ReservationDTO;
import com.example.BigBowlProjekt.dto.SaleRequestDTO;
import com.example.BigBowlProjekt.dto.UserTypeDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.model.AuditLog;
import com.example.BigBowlProjekt.repository.AuditLogRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    public void saveLog(String user, String action, String description) {
        AuditLog auditLog = new AuditLog(user, action, LocalDateTime.now(), description);
        auditLogRepository.save(auditLog);
    }

    public String userGrabber(HttpSession session) {
        UserTypeDTO userTypeDTO = (UserTypeDTO) session.getAttribute("user");
        if (userTypeDTO.userType().equals("admin")) {
            return "Admin";
        }
        return "Employee";
    }

    public String actionHandler(String action) {
        return switch (action) {
        case "POST" -> "Opprettet";
        case "PUT" -> "Opdateret";
        case "DELETE" -> "Slettet";
        default -> throw new IllegalArgumentException("Invalid action " +  action);
        };
    }

    public String reservationDescriptionMaker(ReservationDTO reservationDTO, String action) {
        String activitiesNameOnly =
                reservationDTO.activities().stream().map(activityDTO ->
                        activityDTO.type().name().toLowerCase()).collect(Collectors.joining(", "));
        String clientName = reservationDTO.name();
        String activitySingularOrPlural = reservationDTO.activities().size() == 1 ? "aktiviteten" : "aktiviteterne";

     return actionHandler(action) + " en reservation til " + clientName + " med " + activitySingularOrPlural + " " + activitiesNameOnly;
    }

    public String shiftDescriptionMaker(WorkingShiftDTO workingShiftDTO, String action) {
        String name = workingShiftDTO.employeeDTO().firstName();
        LocalDate date = workingShiftDTO.date();
        LocalTime startTime = workingShiftDTO.startTime();
        LocalTime endTime = workingShiftDTO.endTime();
        return actionHandler(action) + " arbejdstid til " + name + " den " + date + " kl " + startTime + "-" + endTime ;
    }

    public String saleDescriptionMaker(SaleRequestDTO saleRequestDTO, String action) {
        String saleItems = saleRequestDTO.saleItems().stream().map(saleItemDTO -> String.valueOf(saleItemDTO.productId()) + " x" + saleItemDTO.quantity()).collect(Collectors.joining(", "));
        return actionHandler(action) + " salg " + saleItems;
    }

    public void logHandler(HttpSession session, String action, ReservationDTO reservationDTO) {
        saveLog(userGrabber(session), actionHandler(action), reservationDescriptionMaker(reservationDTO, actionHandler(action)));
    }

    public void logHandler(HttpSession session, String action, WorkingShiftDTO workingShiftDTO) {
        saveLog(userGrabber(session), actionHandler(action), shiftDescriptionMaker(workingShiftDTO, actionHandler(action)));
    }

    public void logHandler(HttpSession session, String action, SaleRequestDTO saleRequestDTO) {
        saveLog(userGrabber(session), actionHandler(action), saleDescriptionMaker(saleRequestDTO, actionHandler(action)));
    }
}
