package com.app.reservation.reservation_app.models;

import com.app.reservation.reservation_app.models.enums.ResourceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "resources")
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @NotBlank
    @JoinColumn(unique = true)
    private String name;

    @NotNull
    private Integer capacity;

    @NotNull
    // It is neccesary if you want to store a boolean value (in database it will be saved as 1 or 0)
    @Column(columnDefinition = "TINYINT(1)")
    private Boolean active;

    public Resource(ResourceType type, String name, Integer capacity, Boolean active) {
        this.type = type;
        this.name = name;
        this.capacity = capacity;
        this.active = active;
    }

    public Resource() {
        this(null, null, null, null);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return "Resource [id=" + id + ", type=" + type + ", name=" + name + ", capacity=" + capacity + ", active="
                + active + "]";
    }
}
