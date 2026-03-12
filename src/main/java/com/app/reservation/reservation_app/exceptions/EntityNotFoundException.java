package com.app.reservation.reservation_app.exceptions;

public class EntityNotFoundException extends RuntimeException {
    public EntityNotFoundException(String entity, Long id) {
        super("The " + entity + " with id " + id + " does not exist");
    }
}
