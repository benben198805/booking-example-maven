package com.ben.bookingexamplemaven.presentation.exception;

public class NotFoundException extends AppException {
    public NotFoundException(String code, String message) {
        super(code, message);
    }
}
