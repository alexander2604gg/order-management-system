package com.alexandersaul.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.FAILED_DEPENDENCY)
public class NotReturnedDataException extends RuntimeException{
    public NotReturnedDataException (String service){
        super(service + "returned not data");
    }
}
