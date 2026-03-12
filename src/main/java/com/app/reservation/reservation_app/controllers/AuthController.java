package com.app.reservation.reservation_app.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.app.reservation.reservation_app.dto.LoginRequest;
import com.app.reservation.reservation_app.dto.RegisterRequest;
import com.app.reservation.reservation_app.services.AuthService;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;


@RestController
@RequestMapping("/api/auth")
@AllArgsConstructor
public class AuthController {

    private final AuthService authServ;

    @PostMapping("/register")
    public ResponseEntity<?> register(@Valid @RequestBody RegisterRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(authServ.register(request));
    }

    @PostMapping("/login")
    public ResponseEntity<?> authenticate(@Valid @RequestBody LoginRequest request) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authServ.login(request));
    }
    
    // This endpoint only needs the token which is on header request ("Beare aefwefpoqe....")
    // The token must be the refresh-token that we get when authenticate is executed
    @PostMapping("/refresh")
    public ResponseEntity<?> refreshToken(@RequestHeader(HttpHeaders.AUTHORIZATION) String authHeader) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authServ.refreshToken(authHeader));
    }
    
    @GetMapping("/confirm")
    public ResponseEntity<?> registerConfirm(@RequestParam String token) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(authServ.confirm(token));
    }

}
