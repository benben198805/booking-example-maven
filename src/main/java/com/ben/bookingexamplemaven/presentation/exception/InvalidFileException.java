package com.ben.bookingexamplemaven.presentation.exception;

public class InvalidFileException extends AppException {

    public InvalidFileException(String code, String message) {
        super(code, message);
    }
}
