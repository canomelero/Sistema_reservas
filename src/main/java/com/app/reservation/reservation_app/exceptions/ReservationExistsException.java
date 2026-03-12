package com.app.reservation.reservation_app.exceptions;

public class ReservationExistsException extends RuntimeException {
    public ReservationExistsException() {
        super("Cannot do a reservation due to date or hour");
    }
}
