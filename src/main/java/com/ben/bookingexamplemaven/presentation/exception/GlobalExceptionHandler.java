package com.ben.bookingexamplemaven.presentation.exception;

import com.ben.bookingexamplemaven.presentation.dto.response.common.ErrorResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handleNotFoundException(NotFoundException exception) {
        log.warn(exception.getMessage());
        return ErrorResponse.of(HttpStatus.NOT_FOUND.value(), exception.getCode(), exception.getMessage());
    }
}
