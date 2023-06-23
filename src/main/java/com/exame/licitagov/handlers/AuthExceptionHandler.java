package com.exame.licitagov.handlers;

import com.exame.licitagov.exceptions.AlreadyExistingUsernameException;
import com.exame.licitagov.exceptions.InvalidCredentialsException;
import io.jsonwebtoken.ExpiredJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@ControllerAdvice
public class AuthExceptionHandler extends ExceptionManager {

    @ResponseBody
    @ExceptionHandler({ AuthenticationException.class })
    public ResponseEntity<Object> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.UNAUTHORIZED, request, Optional.empty());
    }

    @ResponseBody
    @ExceptionHandler({ ExpiredJwtException.class })
    public ResponseEntity<Object> handleExpiredToken(ExpiredJwtException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.UNAUTHORIZED, request, Optional.empty());
    }

    @ResponseBody
    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.NOT_FOUND, request, Optional.empty());
    }

    @ResponseBody
    @ExceptionHandler({ AlreadyExistingUsernameException.class })
    protected ResponseEntity<Object> handleRegisterDuplicateUsername(AlreadyExistingUsernameException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request, Optional.empty());
    }

    @ResponseBody
    @ExceptionHandler({ InvalidCredentialsException.class })
    protected ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request, Optional.empty());
    }
}
