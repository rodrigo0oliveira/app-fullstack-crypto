package br.com.backend.blog_security.application;

import br.com.backend.blog_security.domain.dto.UserRequiredDto;
import br.com.backend.blog_security.exceptions.SignupException;
import br.com.backend.blog_security.infra.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ValidateSignup {

    private final UserRepository userRepository;

    public void validateSignup(UserRequiredDto userRequiredDto){
        if(userRepository.findByUsername(userRequiredDto.userName()).isPresent()){
            throw new SignupException("Nome de usuário já está cadastrado, por favor escolha outro.");
        }
        if(userRepository.findByEmail(userRequiredDto.email()).isPresent()){
            throw new SignupException("O e-mail informado já está cadastrado.");
        }
    }
}
