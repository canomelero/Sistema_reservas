package com.app.reservation.reservation_app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.reservation.reservation_app.dto.LoginRequest;
import com.app.reservation.reservation_app.dto.RegisterRequest;
import com.app.reservation.reservation_app.dto.TokenResponse;
import com.app.reservation.reservation_app.exceptions.EntityNotFoundException;
import com.app.reservation.reservation_app.models.Token;
import com.app.reservation.reservation_app.models.User;
import com.app.reservation.reservation_app.repositories.TokenRepository;
import com.app.reservation.reservation_app.repositories.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private TokenRepository tokenRep;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private UserRepository userRep;

    @Autowired
    private JwtService jwtServ;

    @Autowired
    private AuthenticationManager authManager;

    //@Autowired
    //private EmailServiceImpl emailServ;

    // In this case, the logic is to create a different token than the authentication token
    // It is better to have a table for authentication tokens and another for confirmation tokens
    // In the other way, the user table will have a confirmed boolean field and if user click on 
    // confirm button, this field change to true
    @Override
    public TokenResponse register(RegisterRequest request) {
        // Check if email exists
        Optional<User> userExists = userRep.findByEmail(request.getEmail());

        if(userExists.isPresent()) {
            throw new DataIntegrityViolationException("Email already exists on database");
        }

        // Create user 
        User newUser = new User(
            request.getName(),
            request.getEmail(),
            encoder.encode(request.getPassword())   // Encrypt the password
        );

        // Save user on database table
        User savedUser = userRep.save(newUser);

        // Generate a token for the current user
        String token = jwtServ.generateToken(savedUser);
        String refreshToken = jwtServ.generateRefreshToken(savedUser);

        // Saved the token created
        Token newToken = new Token(token, false, false, savedUser);
        tokenRep.save(newToken);

        // Send the email with message
        //String link = "http://127.0.0.1:8080/auth/confirm?token=" + token;
        //emailServ.sendEmail(request.getEmail(), 
        //                    buildEmail(request.getName(), link));

        return new TokenResponse(token, refreshToken);
    }

    @Override
    public String confirm(String token) {
        // Extract the token information to check if it is correct
        String userEmail = jwtServ.extractUsername(token);

        if(userEmail == null) {
            throw new IllegalArgumentException("Invalid token");
        }

        // Check if user exists on database
        User userDb = userRep.findByEmail(userEmail)
                                .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        // Check if the token is valid
        if(!jwtServ.isTokenValid(token, userDb)) {
            throw new IllegalArgumentException("Token is not valid");
        }

        // Set token confirmed value to true and save it
        Token tokenDb = tokenRep.findByToken(token)
                                    .orElseThrow(() -> 
                                        new EntityNotFoundException("Token not found", null)
                                    );
        
        tokenDb.setConfirmed(true);                                
        tokenRep.save(tokenDb);

        return "confirmed";
    }

    @Override
    public TokenResponse login(LoginRequest request) {
        // We can use the authManager to do this
        authManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                request.getEmail(),
                request.getPassword()
            )
        );

        // Check if user exists
        User user = userRep.findByEmail(request.getEmail()).orElseThrow();

        // Generate new tokens
        String token = jwtServ.generateToken(user);
        String refreshToken = jwtServ.generateRefreshToken(user);

        // Revoke all tokens that user has before 
        revokeAllTokens(user);

        // Saved the token created
        Token newToken = new Token(token, false, false, user);
        tokenRep.save(newToken);

        return new TokenResponse(token, refreshToken);
    }

    @Override
    public TokenResponse refreshToken(String authHeader) {
        if(authHeader == null || !authHeader.contains("Bearer ")) {
            throw new IllegalArgumentException("Invalid Bearer token");
        }

        // Extract the user email from token
        String refreshToken = authHeader.substring(7);
        String userEmail = jwtServ.extractUsername(refreshToken);

        if(userEmail == null) {
            throw new IllegalArgumentException("Invalid Refresh token");
        }

        // Check if the user exists on database
        User userDb = userRep.findByEmail(userEmail)
                                .orElseThrow(() -> new UsernameNotFoundException(userEmail));
        
        // Check if the token is valid
        if(!jwtServ.isTokenValid(refreshToken, userDb)) {
            throw new IllegalArgumentException("Refresh token is expired");
        }

        // Generate new tokens
        String token = jwtServ.generateToken(userDb);

        // Revoke all tokens that user has before 
        revokeAllTokens(userDb);

        // Saved the token created
        Token newToken = new Token(token, false, false, userDb);
        tokenRep.save(newToken);

        return new TokenResponse(token, refreshToken);
    }

    private void revokeAllTokens(User user) {
        List<Token> userTokenRegisters = tokenRep.findByUserId(user.getId());

        userTokenRegisters.forEach(register -> {
            register.setRevoked(true);
            register.setExpired(true);
        });

        tokenRep.saveAll(userTokenRegisters);
    }

    // private String buildEmail(String name, String link) {
    //     return "<!DOCTYPE html>" +
    //             "<html>" +
    //             "<head>" +
    //             "<style>" +
    //             "body { font-family: Arial, sans-serif; padding: 20px; background-color: #f4f4f4; }" +
    //             ".container { background-color: white; padding: 30px; border-radius: 10px; max-width: 600px; margin: 0 auto; }" +
    //             "h1 { color: #333; }" +
    //             ".button { display: inline-block; padding: 15px 30px; background-color: #4CAF50; color: white; text-decoration: none; border-radius: 5px; font-weight: bold; margin-top: 20px; }" +
    //             ".button:hover { background-color: #45a049; }" +
    //             "</style>" +
    //             "</head>" +
    //             "<body>" +
    //             "<div class='container'>" +
    //             "<h1>¡Hola " + name + "!</h1>" +
    //             "<p>Te has registrado exitosamente en la aplicación. Por favor, confirma tu cuenta haciendo clic en el siguiente botón:</p>" +
    //             "<a href='" + link + "' class='button'>Confirmar cuenta</a>" +
    //             "<p style='margin-top: 30px; color: #666; font-size: 14px;'>Si no puedes hacer clic en el botón, copia y pega este enlace en tu navegador:</p>" +
    //             "<p style='color: #666; font-size: 12px; word-break: break-all;'>" + link + "</p>" +
    //             "</div>" +
    //             "</body>" +
    //             "</html>";
    // }
}
