package com.example.BigBowlProjekt.exception;

import org.springframework.data.crossstore.ChangeSetPersister;

public class WorkingShiftNotFoundException extends RuntimeException {
    public WorkingShiftNotFoundException(String message) {
        super((message));
    }
}
