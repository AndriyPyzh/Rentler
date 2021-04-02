package com.rentler.auth.exception.exceptions;

public class BadUsernameOrPasswordException extends RuntimeException {
    public BadUsernameOrPasswordException(String message) {
        super(message);
    }
}