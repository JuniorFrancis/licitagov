package com.exame.licitagov.handlers;

import com.exame.licitagov.exceptions.UnexpectedExternalException;
import com.exame.licitagov.models.errors.DefaultErrorResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.SocketTimeoutException;
import java.time.LocalDateTime;

public class ExternalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler( {UnexpectedExternalException.class, ExhaustedRetryException.class, SocketTimeoutException.class})
    public ResponseEntity<Object> handleUnexpectedExternalError(Exception ex, WebRequest request){

        DefaultErrorResponse defaultErrorResponse = new DefaultErrorResponse.Builder()
                .withResponseDate(LocalDateTime.now())
                .withResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
                .withCause("External API Error")
                .withCalledMethod(request.getDescription(false).substring(4))
                .build();

        return handleExceptionInternal(ex, defaultErrorResponse, new HttpHeaders(), HttpStatus.SERVICE_UNAVAILABLE, request);

    }
}
