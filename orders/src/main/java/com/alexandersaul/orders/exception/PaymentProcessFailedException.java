package com.alexandersaul.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.PROCESSING)
public class PaymentProcessFailedException  extends  RuntimeException {
    public PaymentProcessFailedException (Throwable throwable){
        super("The payment process failed : " + throwable.getMessage());
    }
}
