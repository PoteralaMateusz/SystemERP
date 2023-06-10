package com.mateusz.SystemERP.item.exceptions;

public class ItemNotFoundException extends RuntimeException {

    public ItemNotFoundException(String message) {
        super(message);
    }public ItemNotFoundException(Long itemId) {
        super("Item with id " + itemId + " does not exist.");
    }
}
