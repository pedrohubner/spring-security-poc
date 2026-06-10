package br.com.pedrohubner.springsecuritypoc.controller.login;

import br.com.pedrohubner.springsecuritypoc.impl.auth.AuthService;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.RefreshRequest;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.RefreshResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/refresh")
@RequiredArgsConstructor
public class RefreshRestController {
    private final AuthService authService;

    @PostMapping
    public RefreshResponse refresh(@RequestBody RefreshRequest request) {
        return authService.refresh(request);
    }
}
