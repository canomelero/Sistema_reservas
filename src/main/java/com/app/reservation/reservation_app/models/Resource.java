package com.app.reservation.reservation_app.models;

import com.app.reservation.reservation_app.models.enums.ResourceType;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "resources")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Resource {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{resource.type.notnull}")
    @Enumerated(EnumType.STRING)
    private ResourceType type;

    @NotBlank(message = "{resource.name.notblank}")
    @Column(unique = true)
    private String name;

    @NotNull(message = "{resource.capacity.notnull}")
    private Integer capacity;

    @NotNull(message = "{resource.active.notnull}")
    @Column(nullable = false)
    private Boolean active;

    public Resource(ResourceType type, String name, Integer capacity, Boolean active) {
        this.type = type;
        this.name = name;
        this.capacity = capacity;
        this.active = active;
    }

    @Override
    public String toString() {
        return "Resource [id=" + id + ", type=" + type + ", name=" + name + ", capacity=" + capacity + ", active="
                + active + "]";
    }
}
