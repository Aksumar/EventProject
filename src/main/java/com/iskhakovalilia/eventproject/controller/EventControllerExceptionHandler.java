package com.iskhakovalilia.eventproject.controller;

import com.iskhakovalilia.eventproject.exceptions.NoSuchGeoObjectException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.validation.ConstraintViolationException;

import static org.springframework.http.HttpStatus.UNPROCESSABLE_ENTITY;

@RestControllerAdvice
public class EventControllerExceptionHandler {

    /**
     * Handles validation exceptions (a filed is wrongly filled)
     *
     * @return ResponseEntity with code 422 - UNPROCESSABLE_ENTITY
     */
    @ExceptionHandler({ConstraintViolationException.class, NoSuchGeoObjectException.class})
    public ResponseEntity handleValidationException() {
        return ResponseEntity.status(UNPROCESSABLE_ENTITY).body("There is invalid fields in request");
    }
}
