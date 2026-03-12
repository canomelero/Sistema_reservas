package com.app.reservation.reservation_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "tokens")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Token {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "{user.token.notblank}")
    @Column(unique = true)
    private String token;

    @NotNull(message = "{user.token.revoked.notnull}")
    @Column(nullable = false)
    private Boolean revoked;

    @NotNull(message = "{user.token.expired.notnull}")
    @Column(nullable = false)
    private Boolean expired;

    @NotNull(message = "{user.token.confirmed.notnull}")
    @Column(nullable = false)
    private Boolean confirmed;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Token(String token, Boolean revoked, Boolean expired, Boolean confirmed, User user) {
        this.token = token;
        this.revoked = revoked;
        this.expired = expired;
        this.user = user;
        this.confirmed = confirmed;
    }

    @Override
    public String toString() {
        return "Token [id=" + id + ", token=" + token + ", revoked=" + revoked + ", expired=" + expired + 
                ", confirmed=" + confirmed + ", user=" + user + "]";
    }
}
