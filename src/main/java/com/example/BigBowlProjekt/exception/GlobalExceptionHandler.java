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

    @ExceptionHandler(NotFoundException.class)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(404);
        pd.setTitle("Not Found Exception");
        pd.setDetail(ex.getMessage());
        return pd;
    }

    @ExceptionHandler(WorkingShiftOverlapException.class)
    public ProblemDetail handleWorkingShiftOverlapException(WorkingShiftOverlapException ex) {
        ProblemDetail pd = ProblemDetail.forStatus(400);
        pd.setTitle("Shift Overlap Exception");
        pd.setDetail(ex.getMessage());
        return pd;
    }
}
