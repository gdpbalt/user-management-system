package com.example.usermanagementsystem.controller;

import com.example.usermanagementsystem.dto.response.ErrorMessageDto;
import com.example.usermanagementsystem.exception.AuthenticationException;
import com.example.usermanagementsystem.exception.EntityNotFoundException;
import com.example.usermanagementsystem.exception.InvalidJwtAuthenticationException;
import com.example.usermanagementsystem.exception.RefreshTokenException;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@Log4j2
@RestControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = {AuthenticationException.class, RefreshTokenException.class})
    public ResponseEntity<ErrorMessageDto> AuthenticationHandler(
            AuthenticationException exception) {
        ErrorMessageDto body = new ErrorMessageDto();
        body.setStatusCode(HttpStatus.FORBIDDEN.value());
        body.setTimestamp(new Date());
        body.setMessage(List.of(exception.getMessage()));
        log.warn("Exception: {}", body);
        return new ResponseEntity<>(body, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<ErrorMessageDto> entityNotFoundHandler(
            EntityNotFoundException exception) {
        ErrorMessageDto body = new ErrorMessageDto();
        body.setStatusCode(HttpStatus.NOT_FOUND.value());
        body.setTimestamp(new Date());
        body.setMessage(List.of(exception.getMessage()));
        log.warn("Exception: {}", body);
        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(value = InvalidJwtAuthenticationException.class)
    public ResponseEntity<ErrorMessageDto> InvalidJwtAuthenticationHandler(
            InvalidJwtAuthenticationException exception) {
        ErrorMessageDto body = new ErrorMessageDto();
        body.setStatusCode(HttpStatus.UNAUTHORIZED.value());
        body.setTimestamp(new Date());
        body.setMessage(List.of(exception.getMessage()));
        log.warn("Exception: {}", body);
        return new ResponseEntity<>(body, HttpStatus.UNAUTHORIZED);
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        ErrorMessageDto body = new ErrorMessageDto();
        body.setTimestamp(new Date());
        body.setStatusCode(status.value());
        List<String> errors = ex.getBindingResult()
                .getAllErrors()
                .stream()
                .map(this::getErrorMessage)
                .collect(Collectors.toList());
        body.setMessage(errors);
        log.warn("Validation error: {}", body);
        return new ResponseEntity<>(body, headers, status);
    }

    private String getErrorMessage(ObjectError error) {
        if (error instanceof FieldError) {
            String field = ((FieldError) error).getField();
            return field + ": " + error.getDefaultMessage();
        }
        return error.getDefaultMessage();
    }
}
