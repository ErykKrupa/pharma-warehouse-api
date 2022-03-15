package com._4itsolutions.pharmawarehouseapi.controller;

import com._4itsolutions.pharmawarehouseapi.exception.RequestProcessingException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionControllerAdvice {
    private final String incorrectDataMessage;

    public ExceptionControllerAdvice(@Value("${error.message.incorrect-data}") String incorrectDataMessage) {
        this.incorrectDataMessage = incorrectDataMessage;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String invalidDataFormatHandler(MethodArgumentNotValidException ex) {
        return incorrectDataMessage + " " + ex.getMessage();
    }

    @ExceptionHandler(RequestProcessingException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String mainExceptionHandler(RuntimeException ex) {
        return ex.getMessage();
    }
}
