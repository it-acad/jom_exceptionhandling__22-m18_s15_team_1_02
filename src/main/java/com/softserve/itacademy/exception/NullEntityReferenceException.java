package com.softserve.itacademy.exception;

public class NullEntityReferenceException extends Exception {
    public NullEntityReferenceException() {
        super("Reference to an entity is null");
    }

    public NullEntityReferenceException(String message) {
        super(message);
    }
}
