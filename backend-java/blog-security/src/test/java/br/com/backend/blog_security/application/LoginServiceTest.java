package br.com.backend.blog_security.application;

import br.com.backend.blog_security.application.impl.LoginServiceImpl;
import br.com.backend.blog_security.domain.TokenResponse;
import br.com.backend.blog_security.domain.dto.LoginDto;
import br.com.backend.blog_security.security.TokenProvider;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {

    @InjectMocks
    private LoginServiceImpl loginService;

    @Mock
    private AuthenticationManager authenticationManager;

    @Mock
    private TokenProvider tokenProvider;

    @Test
    void testLoginWhenCredentialsAreValidShouldReturnTokenResponse() throws Exception {
        LoginDto loginDto = new LoginDto("rodrigo","123456789");

        UsernamePasswordAuthenticationToken userAuthentication
                = new UsernamePasswordAuthenticationToken(loginDto.userName(),loginDto.password());

        Authentication authentication = mock(Authentication.class);
        TokenResponse tokenResponse =
                new TokenResponse("mock-token",1000L, loginDto.userName());

        when(authenticationManager.authenticate(userAuthentication))
                .thenReturn(authentication);

        when(tokenProvider.generateToken(authentication)).thenReturn(tokenResponse);

        TokenResponse actual = loginService.login(loginDto);

        Assertions.assertEquals(tokenResponse,actual);

        verify(authenticationManager).authenticate(userAuthentication);
        verify(tokenProvider).generateToken(authentication);
    }

    @Test
    void testLoginWhenCredentialsAreInvalidShouldThrowAuthenticationException() throws Exception {
        LoginDto loginDto = new LoginDto("rodrigo","wrongpassword");

        UsernamePasswordAuthenticationToken userAuthentication
                = new UsernamePasswordAuthenticationToken(loginDto.userName(),loginDto.password());

        Authentication authentication = mock(Authentication.class);
        TokenResponse tokenResponse =
                new TokenResponse("mock-token",1000L, loginDto.userName());

        when(authenticationManager.authenticate(userAuthentication))
                .thenThrow(BadCredentialsException.class);

        Exception exception =
                Assertions.assertThrows(Exception.class,()-> loginService.login(loginDto));

        String expectedMessage = "Usuário ou senha inválidos";

        Assertions.assertEquals(expectedMessage,exception.getMessage());
    }
}
