package com.exame.licitagov.exceptions;

public class UnexpectedExternalException extends RuntimeException{
    public UnexpectedExternalException() {
    }

    public UnexpectedExternalException(String message) {
        super(message);
    }

    public UnexpectedExternalException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnexpectedExternalException(Throwable cause) {
        super(cause);
    }

    public UnexpectedExternalException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
