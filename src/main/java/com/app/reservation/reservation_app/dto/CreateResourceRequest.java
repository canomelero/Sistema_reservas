package com.app.reservation.reservation_app.dto;

import com.app.reservation.reservation_app.models.enums.ResourceType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CreateResourceRequest {
    @NotNull(message = "{resource.type.notnull}")
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @NotBlank(message = "{resource.name.notblank}")
    private String name;

    @NotNull(message = "{resource.capacity.notnull}")
    private Integer capacity;

    @NotNull(message = "{resource.active.notnull}")
    private Boolean active;
}
