package br.com.backend.blog_security.application;

import br.com.backend.blog_security.application.impl.SignupServiceImpl;
import br.com.backend.blog_security.domain.Role;
import br.com.backend.blog_security.domain.User;
import br.com.backend.blog_security.domain.dto.UserRequiredDto;
import br.com.backend.blog_security.infra.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.Collections;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class SignupServiceTest {

    @InjectMocks
    private SignupServiceImpl signupService;

    @Mock
    private RoleService roleService;

    @Mock
    private ValidateSignup validateSignup;

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    static User user;

    @BeforeAll
    static void setup(){
        user = User.builder()
                .id(1L)
                .roles(Collections.singletonList(new Role(1L,"ROLE_USER")))
                .username("rodrigo")
                .password("123456789")
                .email("rodrigo@gmail.com")
                .build();
    }

    @Test
    void testSignupWhenDataIsCorrectShouldReturnSuccess(){

        UserRequiredDto userRequiredDto = new UserRequiredDto("rodrigo"
                ,"rodrigo@gmail.com","123456789");

        when(this.roleService.findRoleByName(any(String.class)))
                .thenReturn(new Role(1L,"ROLE_USER"));

        when(this.userRepository.save(any(User.class))).thenReturn(user);

        doNothing().when(this.validateSignup).validateSignup(userRequiredDto);

        String actualMessage = signupService.signup(userRequiredDto);

        verify(this.validateSignup).validateSignup(userRequiredDto);
        verify(this.userRepository).save(any(User.class));
        verify(this.roleService).findRoleByName("ROLE_USER");

        Assertions.assertTrue(actualMessage.contains("Conta criada com sucesso!"));
    }


}
