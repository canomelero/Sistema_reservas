package com.app.reservation.reservation_app.services;

import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.app.reservation.reservation_app.dto.CreateUserRequest;
import com.app.reservation.reservation_app.exceptions.EntityNotFoundException;
import com.app.reservation.reservation_app.models.User;
import com.app.reservation.reservation_app.repositories.UserRepository;

import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRep;

    @Transactional
    @Override
    public User create(CreateUserRequest request) {
        User user = new User(
            request.getName(),
            request.getEmail(),
            request.getPassword()
        );

        return userRep.save(user);
    }

    @Transactional(readOnly = true)
    @Override
    public User getUserById(Long id) {
        Optional<User> userDb = userRep.findById(id);

        if(userDb.isEmpty()) {
            throw new EntityNotFoundException("user", id);
        }

        return userDb.get();
    }
}
