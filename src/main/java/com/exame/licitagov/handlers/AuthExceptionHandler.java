package com.exame.licitagov.handlers;

import com.exame.licitagov.exceptions.AlreadyExistingUsernameException;
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

    private static String getCalledMethod(WebRequest request){
        return request.getDescription(false).substring(4);
    }

    private ResponseEntity<Object> handleExceptionManager(Exception ex, HttpStatus httpStatus, WebRequest request){
        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(httpStatus)
                .withCause(ex.getMessage())
                .withCalledMethod(getCalledMethod(request))
                .build();

        return handleExceptionInternal(
                ex,
                defaultErrorResponse, new HttpHeaders(),
                defaultErrorResponse.getResponseStatusDescription(),
                request
        );
    }

    @ExceptionHandler({ AuthenticationException.class })
    @ResponseBody
    public ResponseEntity<Object> handleAuthenticationException(Exception ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ ExpiredJwtException.class })
    @ResponseBody
    public ResponseEntity<Object> handleExpiredToken(ExpiredJwtException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler({ UsernameNotFoundException.class })
    protected ResponseEntity<Object> handleUserNotFound(UsernameNotFoundException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.NOT_FOUND, request);
    }

    @ExceptionHandler({ AlreadyExistingUsernameException.class })
    protected ResponseEntity<Object> handleRegisterDuplicateUsername(AlreadyExistingUsernameException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler({ InvalidCredentialsException.class })
    protected ResponseEntity<Object> handleInvalidCredentials(InvalidCredentialsException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request);
    }
}
