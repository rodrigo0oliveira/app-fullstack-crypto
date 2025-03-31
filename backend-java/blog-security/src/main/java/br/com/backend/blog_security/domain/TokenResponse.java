package br.com.backend.blog_security.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class TokenResponse {

    private String token;

    private long expiresIn;

    private String userEmail;
}
