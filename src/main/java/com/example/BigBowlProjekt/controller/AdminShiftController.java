package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;
import com.example.BigBowlProjekt.service.AdminShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class AdminShiftController {

    private final AdminShiftService adminShiftService;

    public AdminShiftController(AdminShiftService adminShiftService) {
        this.adminShiftService = adminShiftService;
    }

    @PostMapping("/working-shifts")
    public ResponseEntity<WorkingShiftDTO> createWorkingShift(
            @RequestBody WorkingShiftRequest dto) {

        return ResponseEntity.ok(
                adminShiftService.createWorkingShift(dto)
        );
    }

    @PutMapping("/working-shifts/{id}")
    public ResponseEntity<WorkingShiftDTO> updateWorkingShift(
            @PathVariable Long id,
            @RequestBody WorkingShiftDTO dto) {

        return adminShiftService.updateWorkingShift(id, dto)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/working-shifts/{id}")
    public ResponseEntity<Void> deleteWorkingShift(
            @PathVariable Long id) {

        if (!adminShiftService.deleteWorkingShift(id)) {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.noContent().build();
    }
}
