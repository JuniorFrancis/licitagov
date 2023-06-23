package com.exame.licitagov.handlers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.WebRequest;

import java.util.Optional;

@ControllerAdvice
public class DefaultExceptionHandler extends ExceptionManager {

    @ResponseBody
    @ExceptionHandler({ IllegalArgumentException.class  })
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        return handleExceptionManager(ex, HttpStatus.BAD_REQUEST, request, Optional.of("Invalid date provided for query"));
    }
}
