package com.alexandersaul.orders.exception;

public class OrderNotValidToProcessPaymentException extends RuntimeException{
    public OrderNotValidToProcessPaymentException (String currentStatus) {
        super("Not possible to process payment because the order is: " + currentStatus);
    }
}
