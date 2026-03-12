package com.app.reservation.reservation_app.services;

import java.util.Date;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.app.reservation.reservation_app.models.User;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {

    @Value("${security.jwt.secret}")
    private String jwtSecretKey;

    @Value("${security.jwt.expiration}")
    private Long jwtExpiration;

    @Value("${security.jwt.refresh-token.expiration}")
    private Long refreshTokenExpiration;

    @Override
    public String buildToken(User user, Long expiration) {
        return Jwts.builder()
                .id(user.getId().toString())
                .claims(Map.of("name", user.getName()))
                .subject(user.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignKey())
                .compact();
    }

    @Override
    public String generateToken(User user) {
        return buildToken(user, this.jwtExpiration);
    }

    @Override
    public String generateRefreshToken(User user) {
        return buildToken(user, this.refreshTokenExpiration);
    }

    @Override
    public String extractUsername(String token) {
        Claims jwtToken = Jwts.parser()
                            .verifyWith(getSignKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload();
        
        return jwtToken.getSubject();   // subject is the email
    }

    @Override
    public boolean isTokenValid(String token, User user) {
        String userEmail = extractUsername(token);
        return userEmail.equals(user.getEmail()) && !isTokenExpired(token);
    }

    @Override
    public boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser()
                            .verifyWith(getSignKey())
                            .build()
                            .parseSignedClaims(token)
                            .getPayload()
                            .getExpiration();

        return expiration.before(new Date());
    }

    private SecretKey getSignKey() {
        byte[] keyBytes = Decoders.BASE64.decode(this.jwtSecretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
