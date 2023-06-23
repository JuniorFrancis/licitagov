package com.exame.licitagov.validators;

import com.exame.licitagov.exceptions.InvalidCredentialsException;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class Validators {

    public static void isValidCredentials(String username, String password) throws InvalidCredentialsException {
        if( username.isEmpty() || username.isBlank() || password.isEmpty() || password.isBlank() ){
          throw new InvalidCredentialsException("Credentials cannot be empty");
        }
    }

    public static <T> boolean isNullValue(T value) {
        return value == null;
    }

    public static <T> boolean isEmptyList(List<T> value) {
        return value.isEmpty() ;
    }

    public static void isValidDate(String dateString){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMdd");

        DateFormat format = new SimpleDateFormat("yyyyMMdd");
        format.setLenient(false);

        try {
            format.parse(dateString);
        } catch (ParseException e) {
            System.out.println("LOGGING: DATA INFORMADA INVÁLIDA");
            throw new IllegalArgumentException();
        }
    }
}
