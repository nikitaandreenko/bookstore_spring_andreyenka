package com.company.service.exception;

public class EntityNotFoundException extends NullPointerException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
