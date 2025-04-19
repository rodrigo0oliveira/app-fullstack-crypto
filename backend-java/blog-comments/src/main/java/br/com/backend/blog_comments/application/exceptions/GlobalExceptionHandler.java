package br.com.backend.blog_comments.application.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = { CommentNotFoundException.class })
    public ResponseEntity<String> handleCommentNotFoundException(CommentNotFoundException commentNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(commentNotFoundException.getMessage());
    }
}
