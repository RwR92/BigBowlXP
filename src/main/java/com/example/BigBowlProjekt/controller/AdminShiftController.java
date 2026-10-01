package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;
import com.example.BigBowlProjekt.service.WorkingShiftTempService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/working-shifts")
public class AdminShiftController {

    private final WorkingShiftTempService workingShiftTempService;

    public AdminShiftController(WorkingShiftTempService workingShiftTempService) {
        this.workingShiftTempService = workingShiftTempService;
    }

    @PostMapping
    public ResponseEntity<WorkingShiftDTO> createWorkingShift(
            @RequestBody WorkingShiftRequest dto) {

        return ResponseEntity.ok(
                workingShiftTempService.createWorkingShift(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkingShiftDTO> updateWorkingShift(
            @PathVariable Long id,
            @RequestBody WorkingShiftRequest request) {

        WorkingShiftDTO workingShiftDTO = workingShiftTempService.editWorkingShift(id, request);
        return ResponseEntity.ok(workingShiftDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkingShift(
            @PathVariable Long id) {
        workingShiftTempService.deleteWorkingShift(id);
        return ResponseEntity.noContent().build();
    }
}
