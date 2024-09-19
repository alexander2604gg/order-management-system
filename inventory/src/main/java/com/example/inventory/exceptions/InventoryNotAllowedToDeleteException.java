package com.example.inventory.exceptions;

import com.example.inventory.entity.Inventory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InventoryNotAllowedToDeleteException extends IllegalArgumentException{
    public InventoryNotAllowedToDeleteException (String inventoryId){
        super("Inventory with the code " + inventoryId + " not allowed to delete because it already has stock");
    }
}
