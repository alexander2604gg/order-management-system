package com.alexandersaul.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class NotValidArgumentForPaymentException extends IllegalArgumentException{
    public NotValidArgumentForPaymentException (String status){
        super("Status not allowed:" + status);
    }
}
