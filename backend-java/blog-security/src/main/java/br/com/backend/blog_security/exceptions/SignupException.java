package br.com.backend.blog_security.exceptions;

public class SignupException extends RuntimeException
{
    public SignupException(String message) {
        super(message);
    }
}
