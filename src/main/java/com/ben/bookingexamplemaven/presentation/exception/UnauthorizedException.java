package com.ben.bookingexamplemaven.presentation.exception;

public class UnauthorizedException extends AppException {
    public UnauthorizedException(String code, String message) {
        super(code, message);
    }
}
