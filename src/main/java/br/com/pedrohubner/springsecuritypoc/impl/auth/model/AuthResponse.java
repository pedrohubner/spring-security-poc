package br.com.pedrohubner.springsecuritypoc.impl.auth.model;

public record AuthResponse(
        String username,
        String accessToken,
        String refreshToken,
        String accessExpiresAt
) {
}
