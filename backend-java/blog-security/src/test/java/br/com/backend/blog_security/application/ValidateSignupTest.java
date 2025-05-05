package br.com.backend.blog_security.application;

import br.com.backend.blog_security.application.impl.ValidateSignupImpl;
import br.com.backend.blog_security.domain.Role;
import br.com.backend.blog_security.domain.User;
import br.com.backend.blog_security.domain.dto.UserRequiredDto;
import br.com.backend.blog_security.exceptions.SignupException;
import br.com.backend.blog_security.infra.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Collections;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class ValidateSignupTest {

    @InjectMocks
    private ValidateSignupImpl validateSignup;

    static User user;

    @BeforeAll()
    static void setup(){
        user = User.builder()
                .id(1L)
                .roles(Collections.singletonList(new Role(1L,"ROLE_USER")))
                .username("rodrigo")
                .password("123456789")
                .email("rodrigo@gmail.com")
                .build();
    }

    @Mock
    private UserRepository userRepository;

    @Test
    void testValidateSignupWhenCredentialsAreValidShouldDoNothing() {
        UserRequiredDto userRequiredDto =
                new UserRequiredDto("rodrigo","rodrigo@gmail.com","123456789");

        when(userRepository.findByUsername(userRequiredDto.userName())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userRequiredDto.email())).thenReturn(Optional.empty());


        validateSignup.validateSignup(userRequiredDto);

        verify(userRepository).findByUsername(userRequiredDto.userName());
        verify(userRepository, times(1)).findByEmail(userRequiredDto.email());
    }

    @Test
    void testValidateSignupWhenUserNameAlreadyExistShouldThrowSignupException() {
        UserRequiredDto userRequiredDto =
                new UserRequiredDto("rodrigo","rodrigo@gmail.com","123456789");

        when(userRepository.findByUsername(userRequiredDto.userName())).thenReturn(Optional.of(user));

        SignupException exception = Assertions.assertThrows(
                SignupException.class,
                () -> validateSignup.validateSignup(userRequiredDto)
        );

        String expectedMessage = "Nome de usuário já está cadastrado, por favor escolha outro.";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void testValidateSignupWhenEmailAlreadyExistShouldThrowSignupException() {
        UserRequiredDto userRequiredDto =
                new UserRequiredDto("rodrigo","rodrigo@gmail.com","123456789");

        when(userRepository.findByUsername(userRequiredDto.userName())).thenReturn(Optional.empty());
        when(userRepository.findByEmail(userRequiredDto.email())).thenReturn(Optional.of(user));

        SignupException exception = Assertions.assertThrows(
                SignupException.class,
                () -> validateSignup.validateSignup(userRequiredDto)
        );

        String expectedMessage = "O e-mail informado já está cadastrado.";
        String actualMessage = exception.getMessage();

        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}
