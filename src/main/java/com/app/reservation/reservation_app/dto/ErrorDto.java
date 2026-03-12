package com.app.reservation.reservation_app.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@Getter
@Setter
@ToString
public class ErrorDto {
    private String error;
    private String message;
    private Integer status;
}
