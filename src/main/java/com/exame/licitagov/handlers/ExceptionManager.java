package com.exame.licitagov.handlers;

import com.exame.licitagov.models.errors.DefaultErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.LocalDateTime;

public class ExceptionManager extends ResponseEntityExceptionHandler {

    private static String getCalledMethod(WebRequest request){
        return request.getDescription(false).substring(4);
    }

    public ResponseEntity<Object> handleExceptionManager(Exception ex, HttpStatus httpStatus, WebRequest request){
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

    public ResponseEntity<Object> handleExceptionManager(Exception ex, HttpStatus httpStatus, WebRequest request, String message){
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
}
