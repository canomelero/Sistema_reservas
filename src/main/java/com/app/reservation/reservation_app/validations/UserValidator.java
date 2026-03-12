package com.app.reservation.reservation_app.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.app.reservation.reservation_app.models.User;

@Component
public class UserValidator implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return User.class.isAssignableFrom(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        User user = (User) target;

        if(user.getName() == null || user.getName().isBlank()) {
            errors.rejectValue("name", "NotBlank.user.name", "it is necessary a name");
        }

        if(user.getEmail() == null || user.getEmail().isBlank()) {
            errors.rejectValue("email", "NotBlank.user.email", "it is necessary an email");
        }

        if(user.getPassword() == null || user.getPassword().isBlank()) {
            errors.rejectValue("password", "NotBlank.user.password", "it is necessary a password");
        }
    }
}
