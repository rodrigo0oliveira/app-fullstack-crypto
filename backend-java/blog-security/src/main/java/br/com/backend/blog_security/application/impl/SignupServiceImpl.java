package br.com.backend.blog_security.application.impl;

import br.com.backend.blog_security.application.RoleService;
import br.com.backend.blog_security.application.SignupService;
import br.com.backend.blog_security.application.ValidateSignup;
import br.com.backend.blog_security.domain.Role;
import br.com.backend.blog_security.domain.User;
import br.com.backend.blog_security.domain.dto.UserRequiredDto;
import br.com.backend.blog_security.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Slf4j
@RequiredArgsConstructor
@Service
public class SignupServiceImpl implements SignupService {

    private final RoleService roleService;

    private final PasswordEncoder passwordEncoder;

    private final UserRepository userRepository;

    private final ValidateSignup validateSignup;

    @Override
    public String signup(UserRequiredDto userRequiredDto) {

        log.info(userRequiredDto.toString());

        Role role = roleService.findRoleByName("ROLE_USER");

        this.validateSignup.validateSignup(userRequiredDto);

        User user = User.builder()
                .username(userRequiredDto.userName())
                .email(userRequiredDto.email())
                .password(passwordEncoder.encode(userRequiredDto.password()))
                .roles(Collections.singletonList(role))
                .build();

        userRepository.save(user);

        return "Conta criada com sucesso!\n" +
                "Em instantes você será redirecionado para a página de login.";
    }
}
