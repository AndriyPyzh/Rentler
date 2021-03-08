package com.rentler.apartment.exception.exceptions;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException(String message){
        super(message);
    }
}


