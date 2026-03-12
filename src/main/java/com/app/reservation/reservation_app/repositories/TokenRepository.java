package com.app.reservation.reservation_app.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.app.reservation.reservation_app.models.Token;

public interface TokenRepository extends CrudRepository<Token, Long> {
    List<Token> findByUserId(Long id);
    Optional<Token> findByToken(String token);
}
