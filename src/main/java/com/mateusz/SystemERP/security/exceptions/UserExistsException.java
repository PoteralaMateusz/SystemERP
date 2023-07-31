package com.mateusz.SystemERP.security.exceptions;

public class UserExistsException extends RuntimeException{

    public UserExistsException(String user) {
        super("User: " + user +" is exists.");
    }
}
