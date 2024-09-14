package com.alexandersaul.orders.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class StockNotEnoughException extends RuntimeException{

    public StockNotEnoughException (Long productId , int stock ){
        super(String.format("the product with the the id of %s only has a " +
                "maximum of %s items", productId, stock));
    }

}
