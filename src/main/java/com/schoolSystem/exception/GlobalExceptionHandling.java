package com.schoolSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
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

    @ExceptionHandler(EmailDuplicatedException.class)
    public ResponseEntity<String>uniqueEmailHandle(EmailDuplicatedException emailDuplicatedException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(emailDuplicatedException.getMessage());
    }

    @ExceptionHandler(EmailNotFoundException.class)
    public ResponseEntity<String>notFoundHandler(EmailNotFoundException emailNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(emailNotFoundException.getMessage());
    }

    @ExceptionHandler(CursoNotFoundException.class)
    public ResponseEntity<String>notFoundHandler(CursoNotFoundException cursoNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(cursoNotFoundException.getMessage());
    }

    @ExceptionHandler(IncorrectRoleException.class)
    public ResponseEntity<String>invalidRoleHandler(IncorrectRoleException incorrectRoleException){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(incorrectRoleException.getMessage());
    }

    @ExceptionHandler(EstadoNotFoundException.class)
    public ResponseEntity<String> notFoundHandler(EstadoNotFoundException estadoNotFoundException){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(estadoNotFoundException.getMessage());
    }

    
}
