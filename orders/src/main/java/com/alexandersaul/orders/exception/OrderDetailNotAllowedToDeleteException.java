package com.alexandersaul.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.CONFLICT)
public class OrderDetailNotAllowedToDeleteException extends IllegalArgumentException{
    public OrderDetailNotAllowedToDeleteException (Long orderId , String status){
        super("Cannot delete order detail. The order with ID " + orderId + " is in status: " + status);
    }
}
