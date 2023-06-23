package com.exame.licitagov.handlers;

import com.exame.licitagov.exceptions.UnexpectedExternalException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.retry.ExhaustedRetryException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.net.SocketTimeoutException;
import java.util.Optional;

@ControllerAdvice
public class ExternalExceptionHandler extends ExceptionManager {

    @ExceptionHandler( { UnexpectedExternalException.class, ExhaustedRetryException.class, SocketTimeoutException.class})
    public ResponseEntity<Object> handleUnexpectedExternalError(Exception ex, WebRequest request){
        return handleExceptionManager(ex,HttpStatus.SERVICE_UNAVAILABLE, request, Optional.empty());
    }
}
