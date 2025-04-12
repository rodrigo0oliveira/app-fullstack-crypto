package br.com.backend.blog_security.exceptions;

import org.hibernate.PropertyValueException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler({PropertyValueException.class})
    public ResponseEntity<String> handlePropertyValueException(PropertyValueException ex){
        String message = "O "+ex.getPropertyName()+" não foi informado, preencha todos os valores!";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(message);
    }

}
