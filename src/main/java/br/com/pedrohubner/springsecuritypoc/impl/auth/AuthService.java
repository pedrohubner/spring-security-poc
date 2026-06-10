package br.com.pedrohubner.springsecuritypoc.impl.auth;

import br.com.pedrohubner.springsecuritypoc.impl.auth.model.AuthRequest;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.AuthResponse;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.RefreshRequest;
import br.com.pedrohubner.springsecuritypoc.impl.auth.model.RefreshResponse;
import br.com.pedrohubner.springsecuritypoc.impl.user.AuthUser;
import br.com.pedrohubner.springsecuritypoc.impl.user.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenService jwtTokenService;
    private final AuthenticationManager authenticationManager;
    private final RefreshTokenService refreshTokenService;
    private final UserRepository userRepository;

    public AuthResponse authenticate(AuthRequest authRequest) {
        var token = new UsernamePasswordAuthenticationToken(authRequest.username(), authRequest.password());
        var authentication = authenticationManager.authenticate(token);
        var authUser = (AuthUser) authentication.getPrincipal();

        var accessToken = jwtTokenService.generateToken(authentication);
        var accessExpiresAt = jwtTokenService.extractExpirationTime(accessToken);
        var refreshToken = refreshTokenService.generateAndStore(authUser.getId());

        return new AuthResponse(authUser.getUsername(), accessToken, refreshToken, accessExpiresAt);
    }

    public RefreshResponse refresh(RefreshRequest request) {
        var refreshToken = refreshTokenService.validate(request.refreshToken());

        var user = userRepository.findById(refreshToken.getUserId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.UNAUTHORIZED, "User not found"));
        var authUser = new AuthUser(user);
        var authentication = new UsernamePasswordAuthenticationToken(authUser, null, authUser.getAuthorities());

        var accessToken = jwtTokenService.generateToken(authentication);
        var accessExpiresAt = jwtTokenService.extractExpirationTime(accessToken);

        return new RefreshResponse(accessToken, accessExpiresAt);
    }
}
