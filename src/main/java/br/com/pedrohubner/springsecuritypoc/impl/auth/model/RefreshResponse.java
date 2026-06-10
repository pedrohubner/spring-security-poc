package br.com.pedrohubner.springsecuritypoc.impl.auth.model;

public record RefreshResponse(
        String accessToken,
        Long accessExpiresAt
) {
}
