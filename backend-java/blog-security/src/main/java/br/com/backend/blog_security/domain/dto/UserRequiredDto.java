package br.com.backend.blog_security.domain.dto;

public record UserRequiredDto(String userName,String email,String password) {
}
