package com.example.inventory.exceptions;

public class InvalidMovementTypeException extends RuntimeException{
    public InvalidMovementTypeException(String message) {
        super(message);
    }
}
