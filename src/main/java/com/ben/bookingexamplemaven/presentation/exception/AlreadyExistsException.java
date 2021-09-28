package com.ben.bookingexamplemaven.presentation.exception;

public class AlreadyExistsException extends AppException {

    public AlreadyExistsException(String code, String message) {
        super(code, message);
    }
}
