package com.ben.bookingexamplemaven.presentation.exception;

public class InvalidParamException extends AppException {

    public InvalidParamException(String code, String message) {
        super(code, message);
    }
}
