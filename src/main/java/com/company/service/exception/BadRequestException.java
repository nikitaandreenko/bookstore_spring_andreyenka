package com.company.service.exception;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
