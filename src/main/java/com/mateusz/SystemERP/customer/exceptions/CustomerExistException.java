package com.mateusz.SystemERP.customer.exceptions;

public class CustomerExistException extends RuntimeException {

    public CustomerExistException(String message) {
        super(message);
    }
}
