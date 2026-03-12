package com.app.reservation.reservation_app.services;

import com.app.reservation.reservation_app.dto.TokenResponse;
import com.app.reservation.reservation_app.dto.LoginRequest;
import com.app.reservation.reservation_app.dto.RegisterRequest;

public interface AuthService {
    TokenResponse register(RegisterRequest request);
    TokenResponse login(LoginRequest request);
    TokenResponse refreshToken(String token);
    String confirm(String token);
}
