package com.schoolSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String>notFoundHandle(UserNotFoundException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(RoleNotFound.class)
    public ResponseEntity<String>notFoundHandle(RoleNotFound roleNotFound){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(roleNotFound.getMessage());
    }

    @ExceptionHandler(EmailException.class)
    public ResponseEntity<String>uniqueEmailHandle(EmailException emailException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(emailException.getMessage());
    }

    
}
