package com.exame.licitagov.validators;

import com.exame.licitagov.exceptions.InvalidCredentialsException;

import java.util.List;

public class Validators {

    public static void isValidCredentials(String username, String password) throws InvalidCredentialsException {
        if( username.isEmpty() || username.isBlank() || password.isEmpty() || password.isBlank() ){
          throw new InvalidCredentialsException("Credentials cannot be empty");
        }
    }
}
