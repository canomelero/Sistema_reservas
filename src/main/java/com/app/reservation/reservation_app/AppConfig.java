package com.app.reservation.reservation_app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.app.reservation.reservation_app.models.User;
import com.app.reservation.reservation_app.repositories.UserRepository;

@Configuration
@PropertySources({
    @PropertySource("classpath:ValidationMessages.properties")
})
public class AppConfig {

    @Autowired
    private UserRepository userRep;

    // This is a component to inject. It gives the encrypt funcionality
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // This component will allow us to authenticate the user when he do a login
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }

    // The two following components are necessary if we want to use the authenticate method on 
    // AuthenticationManager. This will do Spring automatically

    // This component indicates to Spring how can search if an user exists on database
    @Bean
    public UserDetailsService userDetailsService() {
        // This lambda function is the implemented function of the UserDetailsService interface:
        //      public UserDetails loadUserByUsername(String username)
        // username will be the value that user introduce when he is doing a login (email in this case)
        return username -> {
            User user = userRep.findByEmail(username)
                                    .orElseThrow(() -> new UsernameNotFoundException("User not found"));
            
            // Return the Spring Security User object 
            return org.springframework.security.core.userdetails.User.builder()
                        .username(user.getEmail())
                        .password(user.getPassword())
                        .build(); 
        };
    }

    // This component starts when authenticate() method is executed from login() method
    // First, it calls the userDetailsService that was implemented and get the user if exists
    // Then, check the current password with the password saved on db using the passwordEncoder
    // If everything is all right, return the authentication object
    @Bean
    public AuthenticationProvider authenticationProvider() {
        // How Spring can search an user
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider(userDetailsService());

        // Indicate to Spring how the password is encrypt
        authProvider.setPasswordEncoder(passwordEncoder());

        return authProvider;
    }
}
