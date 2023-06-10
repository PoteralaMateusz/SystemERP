package com.mateusz.SystemERP.order.exceptions;

public class OrderNotFoundException extends RuntimeException {

    public OrderNotFoundException(String message) {
        super(message);
    }
    public OrderNotFoundException(Long orderId) {
        super("Order with id " + orderId + " does not exist.");
    }
}
