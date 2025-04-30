package br.com.backend.blog_security.application;

import br.com.backend.blog_security.domain.dto.UserRequiredDto;


public interface SignupService {

    String signup(UserRequiredDto userRequiredDto);
}
