package com.example.ammercapital.exceptions;

public class NonPositiveException extends RuntimeException{
    public NonPositiveException(String message) {
        super(message);
    }
}
