package com.mateusz.SystemERP.customer.exceptions;

public class CustomerNotFoundException extends RuntimeException {

    public CustomerNotFoundException(String message) {
        super(message);
    }
    public CustomerNotFoundException(Long customerId) {
        super("Customer with id " + customerId + " does not exist.");
    }
}
