package com.ben.bookingexamplemaven.presentation.exception;

public class NotMatchException extends AppException {
    public NotMatchException(String code, String message) {
        super(code, message);
    }
}
