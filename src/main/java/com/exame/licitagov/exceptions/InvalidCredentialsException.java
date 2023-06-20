package com.exame.licitagov.exceptions;

public class InvalidCredentialsException extends IllegalArgumentException {

    public InvalidCredentialsException() {
    }

    public InvalidCredentialsException(String s) {
        super(s);
    }

    public InvalidCredentialsException(String message, Throwable cause) {
        super(message, cause);
    }

    public InvalidCredentialsException(Throwable cause) {
        super(cause);
    }
}
