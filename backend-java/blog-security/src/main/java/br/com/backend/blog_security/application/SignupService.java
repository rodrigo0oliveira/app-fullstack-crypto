package br.com.backend.blog_security.application;

import br.com.backend.blog_security.domain.dto.UserRequiredDto;
import org.springframework.stereotype.Service;

@Service
public interface SignupService {

    String signup(UserRequiredDto userRequiredDto);
}
