package com.exame.licitagov.handlers;

import com.exame.licitagov.exceptions.InvalidCredentialsException;
import com.exame.licitagov.models.errors.DefaultErrorResponse;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class AuthExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) {

        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(HttpStatus.UNAUTHORIZED)
                .withCause("Error on Authentication")
                .withCalledMethod(request.getDescription(false).substring(4))
                .build();

        return handleExceptionInternal(ex, defaultErrorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ ExpiredJwtException.class })
    @ResponseBody
    public ResponseEntity<Object> handleExpiredToken(ExpiredJwtException ex, WebRequest request) {

        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(HttpStatus.UNAUTHORIZED)
                .withCause("Expired Token")
                .withCalledMethod(request.getDescription(false).substring(4))
                .build();

        return handleExceptionInternal(ex, defaultErrorResponse, new HttpHeaders(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(AuthenticationException ex, WebRequest request) {

        String calledMethod = request.getDescription(false).substring(3);

        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(HttpStatus.NOT_FOUND)
                .withCause(ex.getMessage())
                .withCalledMethod(calledMethod)
                .build();


        return handleExceptionInternal(ex, defaultErrorResponse, new HttpHeaders(), HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ InvalidCredentialsException.class })
    protected ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {

        String calledMethod = request.getDescription(false).substring(4);

        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(HttpStatus.BAD_REQUEST)
                .withCause(ex.getMessage())
                .withCalledMethod(calledMethod)
                .build();


        return handleExceptionInternal(ex, defaultErrorResponse, new HttpHeaders(), HttpStatus.BAD_REQUEST, request);
    }
}
