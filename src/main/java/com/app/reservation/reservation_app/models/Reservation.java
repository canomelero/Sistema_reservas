package com.app.reservation.reservation_app.models;

import java.time.LocalDateTime;

import com.app.reservation.reservation_app.models.enums.ReservationStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reservations")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "{reservation.startDate.notnull}")
    private LocalDateTime startDate;

    @NotNull(message = "{reservation.endDate.notnull}")
    private LocalDateTime endDate;

    @NotNull(message = "{reservation.status.notnull}")
    @Enumerated(EnumType.STRING)
    private ReservationStatus status;

    // Many reservations to one user, so FK user_id will be in the reservation table.
    // The FK will be in table that has "many" tuples; in the other way, we will have many tuples with the same user
    // (in user table) with the same values; we don't want this when we work with databases.
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "resource_id")
    private Resource resource;

    public Reservation(LocalDateTime startDate, LocalDateTime endDate, ReservationStatus status, User user,
            Resource resource) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.status = status;
        this.user = user;
        this.resource = resource;
    }

    @Override
    public String toString() {
        return "Reservation [id=" + id + ", startDate=" + startDate + ", endDate=" + endDate + ", status=" + status
                + ", user=" + user + ", resource=" + resource + "]";
    }
}
