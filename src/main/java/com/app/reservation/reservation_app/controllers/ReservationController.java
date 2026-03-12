package com.app.reservation.reservation_app.controllers;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.app.reservation.reservation_app.dto.CreateReservationRequest;
import com.app.reservation.reservation_app.exceptions.EntityNotFoundException;
import com.app.reservation.reservation_app.models.Reservation;
import com.app.reservation.reservation_app.services.ReservationService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping(value = "/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationServ;

    @PostMapping("/")
    public ResponseEntity<?> create(@Valid @RequestBody CreateReservationRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(reservationServ.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getReservationById(@PathVariable Long id) {
        Optional<Reservation> optionalReservation = reservationServ.getReservationById(id);

        if(optionalReservation.isPresent()) {
            return ResponseEntity.ok(optionalReservation.get());
        }

        throw new EntityNotFoundException("reservation", id);
    }
}
