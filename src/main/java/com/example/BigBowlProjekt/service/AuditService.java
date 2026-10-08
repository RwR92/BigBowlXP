package com.example.BigBowlProjekt.service;

import com.example.BigBowlProjekt.dto.UserTypeDTO;
import com.example.BigBowlProjekt.model.AuditLog;
import com.example.BigBowlProjekt.repository.AuditLogRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuditService {
    private final AuditLogRepository auditLogRepository;

    public AuditService(AuditLogRepository auditLogRepository) {
        this.auditLogRepository = auditLogRepository;
    }

    public List<AuditLog> getAllLogs() {
        return auditLogRepository.findAll();
    }

    public void log(String user, String action, String description) {
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
}
