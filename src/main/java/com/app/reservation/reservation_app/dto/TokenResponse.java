package com.app.reservation.reservation_app.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class TokenResponse {
    @NotBlank(message = "{user.token.notblank}")
    private String accesToken;

    @NotBlank(message = "{user.refresh-token.notblank}")
    private String refreshToken;
}
