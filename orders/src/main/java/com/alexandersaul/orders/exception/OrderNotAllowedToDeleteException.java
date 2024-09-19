package com.alexandersaul.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class OrderNotAllowedToDeleteException extends IllegalArgumentException {
    public OrderNotAllowedToDeleteException(String status){
        super("Order not allowed to delete because is already: " + status);
    }
}
