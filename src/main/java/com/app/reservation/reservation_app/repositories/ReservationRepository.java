package com.app.reservation.reservation_app.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.app.reservation.reservation_app.models.Reservation;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
    List<Reservation> findByResourceId(Long id);
}
