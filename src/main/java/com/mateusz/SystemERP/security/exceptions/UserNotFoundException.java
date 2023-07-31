package com.mateusz.SystemERP.security.exceptions;

public class UserNotFoundException extends RuntimeException{

    public UserNotFoundException(String user) {
        super("User: " + user + " is not exist.");
    }
}
