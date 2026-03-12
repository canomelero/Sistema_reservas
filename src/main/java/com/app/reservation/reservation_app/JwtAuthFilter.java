package com.app.reservation.reservation_app;

import java.io.IOException;
import java.util.Optional;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.app.reservation.reservation_app.models.Token;
import com.app.reservation.reservation_app.models.User;
import com.app.reservation.reservation_app.repositories.TokenRepository;
import com.app.reservation.reservation_app.repositories.UserRepository;
import com.app.reservation.reservation_app.services.JwtService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;

// This component will execute the doFilterInternal() method for each request before 
// execute the controller logic
@Component
@AllArgsConstructor
public class JwtAuthFilter extends OncePerRequestFilter {

    private final JwtService jwtServ;
    private final TokenRepository tokenRep;
    private final UserDetailsService userDetailsService;
    private final UserRepository userRep;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        // If the request is to /auth ... endpoints, JWT won't be validate (skip filters)
        if(request.getServletPath().contains("/api/auth/")) {
            filterChain.doFilter(request, response);
            return;
        }
        
        // Get the header and check his format
        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if(authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get the token and check if it is on database
        String token = authHeader.substring(7);
        Optional<Token> optionalToken = tokenRep.findByToken(token);
        
        if(optionalToken.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }

        Token tokenDb = optionalToken.get();

        if(tokenDb == null || tokenDb.getExpired() || tokenDb.getRevoked()) {
            filterChain.doFilter(request, response);
            return;
        }

        // Get the user email and check if exists
        String userEmail = jwtServ.extractUsername(token);

        if(userEmail == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            return;
        }

        UserDetails userDetails = userDetailsService.loadUserByUsername(userEmail);
        Optional<User> optionalUser = userRep.findByEmail(userEmail);

        if(optionalUser.isEmpty()) {
            filterChain.doFilter(request, response);
            return;
        }  
        
        User user = optionalUser.get();
        
        if(!jwtServ.isTokenValid(token, user)) {
            filterChain.doFilter(request, response);
            return;
        }

        // All right -> Accept the request
        // Create an authenticated user for Spring
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
            userDetails,
            null,
            userDetails.getAuthorities()
        );

        // Save request info like IP, session, etc
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

        // Save the request context
        SecurityContextHolder.getContext().setAuthentication(authToken);

        filterChain.doFilter(request, response);
    }

    
}
