package com.example.ammercapital.exceptions;

public class NoAccountException extends RuntimeException{
    public NoAccountException(String message) {
        super(message);
    }
}
