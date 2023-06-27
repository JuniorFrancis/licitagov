package com.exame.licitagov.exceptions;

public class AlreadyExistingUsernameException extends RuntimeException{

    public AlreadyExistingUsernameException(String message) {
        super(message);
    }

}
