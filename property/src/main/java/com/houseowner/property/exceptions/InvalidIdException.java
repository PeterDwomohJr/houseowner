package com.houseowner.property.exceptions;

import org.springframework.stereotype.Component;

@Component
public class InvalidIdException extends RuntimeException {

    private String message;

    public InvalidIdException(String message) {
        super(message);
    }
}
