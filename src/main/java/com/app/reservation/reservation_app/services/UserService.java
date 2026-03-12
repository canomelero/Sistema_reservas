package com.app.reservation.reservation_app.services;

import com.app.reservation.reservation_app.dto.CreateUserRequest;
import com.app.reservation.reservation_app.models.User;

public interface UserService {
    User create(CreateUserRequest user);
    User getUserById(Long id);
}
