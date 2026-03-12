package com.app.reservation.reservation_app.services;

import java.util.Optional;

import com.app.reservation.reservation_app.dto.CreateReservationRequest;
import com.app.reservation.reservation_app.models.Reservation;

public interface ReservationService {
    Reservation create(Long userId, Long resourceId, CreateReservationRequest reservation);
    Optional<Reservation> getReservationById(Long id);
}
