package com.example.BigBowlProjekt.exception;

import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(400);
        pd.setTitle("Illegal argument Exception");
        pd.setDetail(ex.getMessage());
        return pd;
    }

    @ExceptionHandler(WorkingShiftNotFoundException.class)
    public ProblemDetail handleWorkingShiftNotFoundException(WorkingShiftNotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(404);
        pd.setTitle("Not Found Exception");
        pd.setDetail(ex.getMessage());
        return pd;
    }
}
