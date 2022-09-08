package com.company.service.exeption;

public class BadRequestException extends Exception{
    public BadRequestException(String message) {
        super(message);
    }
}
