package com.app.reservation.reservation_app.dto;

import com.app.reservation.reservation_app.models.enums.ResourceType;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CreateResourceRequest {
    @NotNull(message = "Type is required")
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @NotBlank(message = "Name is required")
    private String name;

    @NotNull(message = "Capacity is required")
    private Integer capacity;

    @NotNull(message = "Active is required")
    private Boolean active;

    public CreateResourceRequest(@NotNull ResourceType type, @NotBlank String name, @NotNull Integer capacity,
            @NotNull Boolean active) {
        this.type = type;
        this.name = name;
        this.capacity = capacity;
        this.active = active;
    }

    public CreateResourceRequest() {
    }

    public ResourceType getType() {
        return type;
    }

    public void setType(ResourceType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public Boolean getActive() {
        return active;
    }

    public void setActive(Boolean active) {
        this.active = active;
    }
}
