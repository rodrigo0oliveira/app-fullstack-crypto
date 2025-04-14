package br.com.backend.blog_security.domain.dto;

import jakarta.validation.constraints.NotBlank;


public record UserRequiredDto(

        @NotBlank(message = " O nome de usuário não foi informado, por favor, informe todos os valores!") String userName,
        @NotBlank(message = " O e-mail não foi informado, por favor, informe todos os valores!") String email,
        @NotBlank(message = " A senha não foi informada, por favor, informe todos os valores!") String password
) {
}
