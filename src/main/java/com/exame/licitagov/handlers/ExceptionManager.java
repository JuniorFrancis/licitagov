package com.exame.licitagov.handlers;

import com.exame.licitagov.models.errors.DefaultErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;
import java.util.Optional;

public class ExceptionManager extends ResponseEntityExceptionHandler {
    public ResponseEntity<Object> handleExceptionManager(Exception ex, HttpStatus httpStatus, WebRequest request, Optional<String> rawMessage){

        String message = rawMessage.orElse(ex.getMessage());

        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(httpStatus)
                .withCause(message)
                .withCalledMethod(getCalledMethod(request))
                .build();

        return handleExceptionInternal(
                ex,
                defaultErrorResponse, new HttpHeaders(),
                defaultErrorResponse.getResponseStatusDescription(),
                request
        );
    }

    private static String getCalledMethod(WebRequest request){
        return request.getDescription(false).substring(4);
    }
}
