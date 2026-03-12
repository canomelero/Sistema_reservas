package com.app.reservation.reservation_app.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class RegisterRequest {
    @NotBlank(message = "{user.email.notblank}")
    @Email(message = "{user.email.invalid}")
    private String email;

    // The password must have one capital letter (minimum), one number (minimum) and nine characters (minimum) 
    @NotBlank(message = "{user.password.notblank}")
    @Pattern(
        regexp = "^(?=.*[A-Z])(?=.*[0-9]).{9,}$", 
        message = "{user.password.invalid}"
    )
    private String password;

    @NotBlank(message = "{user.name.notblank}")
    private String name;
}
