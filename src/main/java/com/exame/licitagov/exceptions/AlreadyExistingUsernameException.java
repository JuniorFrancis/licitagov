package com.exame.licitagov.exceptions;

public class AlreadyExistingUsernameException extends RuntimeException{
    public AlreadyExistingUsernameException() {
    }

    public AlreadyExistingUsernameException(String message) {
        super(message);
    }

    public AlreadyExistingUsernameException(String message, Throwable cause) {
        super(message, cause);
    }

    public AlreadyExistingUsernameException(Throwable cause) {
        super(cause);
    }

    public AlreadyExistingUsernameException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
