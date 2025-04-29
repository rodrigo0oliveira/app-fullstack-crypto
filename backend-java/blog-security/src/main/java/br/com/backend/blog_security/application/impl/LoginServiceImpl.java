package br.com.backend.blog_security.application.impl;

import br.com.backend.blog_security.application.LoginService;
import br.com.backend.blog_security.domain.TokenResponse;
import br.com.backend.blog_security.domain.dto.LoginDto;
import br.com.backend.blog_security.security.TokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class LoginServiceImpl implements LoginService {

    private final AuthenticationManager authenticationManager;

    private final TokenProvider provider;

    @Override
    public TokenResponse login(LoginDto loginDto) throws Exception {
        try {
            log.info("tentanto criar autenticacao com "+loginDto.userName()+loginDto.password());
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(loginDto.userName(), loginDto.password()));

            return provider.generateToken(authentication);
        }
        catch (AuthenticationException e) {
            throw new Exception("Usuário ou senha inválidos");
        }
        catch (Exception e) {
            throw new Exception("Erro ao autenticar: "+e.getMessage());
        }
    }
}
