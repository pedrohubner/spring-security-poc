package br.com.pedrohubner.springsecuritypoc.impl.auth.model;

public record RefreshRequest(
        String refreshToken
) {
}
