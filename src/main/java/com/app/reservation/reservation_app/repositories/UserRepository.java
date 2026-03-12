package com.app.reservation.reservation_app.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.reservation.reservation_app.models.User;

public interface UserRepository extends CrudRepository<User, Long> {
    Optional<User> findByEmail(String email);
}
