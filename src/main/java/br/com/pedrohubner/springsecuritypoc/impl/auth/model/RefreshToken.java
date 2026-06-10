package br.com.pedrohubner.springsecuritypoc.impl.auth.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Data
@Entity
@NoArgsConstructor
@Table(name = "refresh_token")
public class RefreshToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tokenHash;
    private Long userId;
    private Instant issuedAt;
    private Instant expiresAt;
    private boolean revoked;
}
