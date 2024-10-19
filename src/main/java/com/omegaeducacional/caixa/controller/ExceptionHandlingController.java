package com.omegaeducacional.caixa.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@ControllerAdvice
public class ExceptionHandlingController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SQLException.class)
    public ResponseEntity<String> handleError(SQLException ex) {
        return ResponseEntity.internalServerError().body("One error occurs. Please contact administrator.");
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<String> handleError(RuntimeException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
