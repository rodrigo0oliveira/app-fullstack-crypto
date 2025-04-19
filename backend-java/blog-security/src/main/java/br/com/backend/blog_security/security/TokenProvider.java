package br.com.backend.blog_security.security;

import br.com.backend.blog_security.domain.TokenResponse;
import br.com.backend.blog_security.domain.User;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nimbusds.jose.JWSVerifier;
import com.nimbusds.jose.crypto.MACVerifier;
import com.nimbusds.jwt.SignedJWT;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Component
@Slf4j
@RequiredArgsConstructor
public class TokenProvider {

    private static final int UNAUTHORIZED = 401;

    private final ObjectMapper objectMapper;

    @Value("${jwt.key}")
    private String jwtKey;

    @Value("${jwt.expiration-time}")
    private Integer expirationTime;

    public TokenResponse generateToken(Authentication authentication) {
        log.info("Gerando token");
        final Date now = new Date();

        long expirationInMillis = expirationTime.longValue() * 1000L;

        Date expirationDate = new Date(System.currentTimeMillis() + expirationInMillis);

        log.info("Buscando usuario");
        final User user = getUsuario(authentication);

        log.info("buscou user"+user.getUsername());


        final String auth = Jwts.builder()
                .setIssuer("WEB TOKEN")
                .setSubject(user.getUsername())
                .setIssuedAt(now)
                .setNotBefore(now)
                .setExpiration(expirationDate)
                .signWith(SignatureAlgorithm.HS256, jwtKey.getBytes(StandardCharsets.UTF_8))
                .compact();

        return TokenResponse.builder()
                .token(auth)
                .expiresIn(expirationInMillis)
                .userName(user.getUsername())
                .build();
    }

    public boolean isValid(String jwt, ServletResponse servletResponse) throws IOException {
        try {
            jwt = extractToken(jwt);
            SignedJWT signedJWT = SignedJWT.parse(jwt);
            JWSVerifier jwsVerifier = new MACVerifier(jwtKey.getBytes(StandardCharsets.UTF_8));
            if (!signedJWT.verify(jwsVerifier)) {
                TokenProvider.log.error("Assinatura inválida");
                ((HttpServletResponse) servletResponse).sendError(UNAUTHORIZED);
                return false;
            }
            Jwts.parser().setSigningKey(jwtKey.getBytes(StandardCharsets.UTF_8))
                    .parseClaimsJws(jwt);
            return true;
        } catch (Exception e) {
            log.error("Token inválido : {}" + e.getMessage());
            ((HttpServletResponse) servletResponse).sendError(UNAUTHORIZED);
            return false;
        }
    }

    public User getUserFromToken(String jwt) throws JsonProcessingException {
        jwt = extractToken(jwt);
        Claims claims = Jwts.parser().setSigningKey(jwtKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(jwt).getBody();

        return objectMapper.readValue(claims.getSubject(), User.class);
    }

    private String extractToken(String authToken) {
        if (authToken.toLowerCase().startsWith("bearer")) {
            return authToken.substring("bearer ".length());
        }
        return authToken;
    }

    public User getUsuario(Authentication authentication) {
        return (User) authentication.getPrincipal();
    }

}
