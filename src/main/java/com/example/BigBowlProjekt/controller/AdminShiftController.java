package com.example.BigBowlProjekt.controller;

import com.example.BigBowlProjekt.dto.WorkingShiftDTO;
import com.example.BigBowlProjekt.dto.WorkingShiftRequest;
import com.example.BigBowlProjekt.service.WorkingShiftService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/working-shifts")
public class AdminShiftController {

    private final WorkingShiftService workingShiftService;

    public AdminShiftController(WorkingShiftService workingShiftService) {
        this.workingShiftService = workingShiftService;
    }

    @GetMapping
    public ResponseEntity<List<WorkingShiftDTO>> showAllWorkingShifts() {
        return ResponseEntity.ok(workingShiftService.getAllWorkingShifts());
    }

    @PostMapping
    public ResponseEntity<WorkingShiftDTO> createWorkingShift(
            @RequestBody WorkingShiftRequest dto) {
        return ResponseEntity.ok(
                workingShiftService.createWorkingShift(dto)
        );
    }

    @PutMapping("/{id}")
    public ResponseEntity<WorkingShiftDTO> updateWorkingShift(
            @PathVariable Long id,
            @RequestBody WorkingShiftRequest request) {

        WorkingShiftDTO workingShiftDTO = workingShiftService.editWorkingShift(id, request);
        return ResponseEntity.ok(workingShiftDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteWorkingShift(
            @PathVariable Long id) {
        workingShiftService.deleteWorkingShift(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<WorkingShiftDTO> getWorkingShiftById(@PathVariable Long id) {
        return ResponseEntity.ok(workingShiftService.getWorkingShiftById(id));
    }
}
