package com.example.BigBowlProjekt.repository;

import com.example.BigBowlProjekt.model.AuditLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuditLogRepositry extends JpaRepository<AuditLog, Long> {
}
