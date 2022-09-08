package com.company.service.exeption;

public class EntityNotFoundException extends NullPointerException {
    public EntityNotFoundException(String message) {
        super(message);
    }
}
