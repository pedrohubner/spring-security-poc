package br.com.pedrohubner.springsecuritypoc.impl.auth;

import br.com.pedrohubner.springsecuritypoc.impl.auth.model.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {
    Optional<RefreshToken> findByTokenHash(String tokenHash);
}
