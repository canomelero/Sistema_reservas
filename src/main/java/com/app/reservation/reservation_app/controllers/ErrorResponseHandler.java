package com.app.reservation.reservation_app.controllers;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.app.reservation.reservation_app.dto.ErrorDto;
import com.app.reservation.reservation_app.exceptions.EntityNotFoundException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import com.app.reservation.reservation_app.exceptions.ReservationExistsException;

@RestControllerAdvice
public class ErrorResponseHandler {

    @ExceptionHandler({
        HttpMessageNotReadableException.class,
        HttpMessageNotWritableException.class
    })
    public ResponseEntity<ErrorDto> badRequest(Exception e) {
        ErrorDto error = new ErrorDto(
            "You didn't execute the endpoint correctly",
            e.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ErrorDto> duplicatedEmail(Exception e) {
        ErrorDto error = new ErrorDto(
            "The email exists on database",
            e.getMessage(),
            HttpStatus.INTERNAL_SERVER_ERROR.value()
        );

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ErrorDto> entityNotFound(Exception e) {
        ErrorDto error = new ErrorDto(
            "The entity doesn't exist on database",
            e.getMessage(),
            HttpStatus.NOT_FOUND.value()
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error); 
    }

    @ExceptionHandler(ReservationExistsException.class)
    public ResponseEntity<ErrorDto> reservationExists(Exception e) {
        ErrorDto error = new ErrorDto(
            "Cannot do the reservation",
            e.getMessage(),
            HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error); 
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorDto> argumentNotValid(MethodArgumentNotValidException e) {
        StringBuilder errors = new StringBuilder();

        e.getBindingResult().getFieldErrors().forEach(error -> {
            errors.append(error.getField() + ": " + error.getDefaultMessage()).append("/");
        });

        ErrorDto errorDto = new ErrorDto(
            "Error in argument validation",
            errors.toString(),
            HttpStatus.BAD_REQUEST.value()
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDto);
    }
}
