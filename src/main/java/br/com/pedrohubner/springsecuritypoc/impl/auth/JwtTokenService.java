package br.com.pedrohubner.springsecuritypoc.impl.auth;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.*;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class JwtTokenService {
    private final JwtEncoder jwtEncoder;
    private final JwtDecoder jwtDecoder;

    public String generateToken(Authentication authentication) {
        var claims = this.build(authentication);
        var encodedParameters = JwtEncoderParameters.from(JwsHeader.with(MacAlgorithm.HS256).build(), claims);
        return this.jwtEncoder.encode(encodedParameters).getTokenValue();
    }

    private JwtClaimsSet build(Authentication authentication) {
        var now = Instant.now();
        return JwtClaimsSet.builder()
                .issuer("self")
                .issuedAt(now)
                .expiresAt(now.plus(15L, ChronoUnit.MINUTES))
                .subject(authentication.getName())
                .claim("scope", this.getScope(authentication))
                .build();
    }

    private String getScope(Authentication authentication) {
        return authentication.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(" "));
    }

    public Long extractExpirationTime(String token) {
        var jwt = jwtDecoder.decode(token);
        var expirationTime = (Instant) jwt.getClaim("exp");
        return expirationTime.toEpochMilli();
    }
}
