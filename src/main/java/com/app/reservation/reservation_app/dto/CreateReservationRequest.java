package com.app.reservation.reservation_app.dto;

import java.time.LocalDateTime;

import com.app.reservation.reservation_app.models.enums.ReservationStatus;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class CreateReservationRequest {
    @NotNull(message = "{reservation.startDate.notblank}")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime startDate;

    @NotNull(message = "{reservation.endDate.notnull}")
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm")
    private LocalDateTime endDate;

    @NotNull(message = "{reservation.status.notnull}")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;
}
