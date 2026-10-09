package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.model.AuditLog;
import com.example.BigBowlProjekt.service.AuditService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/admin/audit")
public class AdminAuditController {
    private final AuditService auditService;

    public AdminAuditController(AuditService auditService) {
        this.auditService = auditService;
    }

    @GetMapping
    public ResponseEntity<List<AuditLog>> getAllLogs() {
        if (auditService.getAllLogs().isEmpty()) {
            ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(auditService.getAllLogs());
    }
}
