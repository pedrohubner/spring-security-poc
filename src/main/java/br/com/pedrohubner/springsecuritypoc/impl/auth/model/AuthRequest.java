package br.com.pedrohubner.springsecuritypoc.impl.auth.model;

public record AuthRequest(
        String username,
        String password
) {
}
