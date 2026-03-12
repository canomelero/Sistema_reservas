package com.app.reservation.reservation_app.services;

import com.app.reservation.reservation_app.models.User;

public interface JwtService {
    String buildToken(User user, Long expiration);
    String generateToken(User user);
    String generateRefreshToken(User user);
    String extractUsername(String token);
    boolean isTokenExpired(String token);
    boolean isTokenValid(String token, User user);
}
