package com.mateusz.SystemERP.product.exceptions;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(String message) {
        super(message);
    }
    public ProductNotFoundException(Long productId) {
        super("Product with id " + productId + " does not exist.");
    }
}
