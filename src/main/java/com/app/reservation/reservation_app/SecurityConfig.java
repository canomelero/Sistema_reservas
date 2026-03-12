package com.app.reservation.reservation_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.reservation.reservation_app.models.Token;
import com.app.reservation.reservation_app.repositories.TokenRepository;

import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;

// Indicates that is a configuration class
// In this class will be established in what endpoints you must have authenticated, what permissions do you 
// need to have to execute it, logout, etc
@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationProvider authProvider;

    @Autowired
    private JwtAuthFilter jwtAuthFilter;

    @Autowired
    private TokenRepository tokenRep;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            .csrf(csrf -> csrf.disable())
            .authorizeHttpRequests(request -> 
                // Allow all requests to auth endpoints (handles trailing slash or subpaths)
                request.requestMatchers("/auth/**").permitAll()
                        .anyRequest()
                        .authenticated()
            )
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
            .authenticationProvider(authProvider)
            .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
            .logout(logout ->
                logout.logoutUrl("/auth/logout")
                        .addLogoutHandler((request, response, authentication) -> {
                            String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
                            logout(authHeader);
                        })
                        .logoutSuccessHandler((request, response, authentication) -> {
                            SecurityContextHolder.clearContext();
                        })
            );

        return http.build();
    }

    private void logout(String header) {
        if(header == null || !header.contains("Bearer ")) {
            throw new IllegalArgumentException("Invalid header");
        }

        String token = header.substring(7);
        Token tokenDb = tokenRep.findByToken(token)
                                .orElseThrow(() -> new IllegalArgumentException("Invalid token"));

        tokenDb.setRevoked(true);
        tokenDb.setExpired(true);
        tokenRep.save(tokenDb);
    }
}
