package com.example.inventory.exceptions;

public class InventoryUpdateException extends RuntimeException{
    public InventoryUpdateException(String message) {
        super(message);
    }
}
