package br.com.pedrohubner.springsecuritypoc.controller.login;

import br.com.pedrohubner.springsecuritypoc.impl.auth.AuthService;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.AuthRequest;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.AuthResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class LoginRestController {
    private final AuthService authService;

    @PostMapping
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.authenticate(request);
    }
}
