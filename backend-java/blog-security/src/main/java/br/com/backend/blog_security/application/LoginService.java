package br.com.backend.blog_security.application;

import br.com.backend.blog_security.domain.TokenResponse;
import br.com.backend.blog_security.domain.dto.LoginDto;

public interface LoginService {

    TokenResponse login(LoginDto loginDto) throws Exception;
}
