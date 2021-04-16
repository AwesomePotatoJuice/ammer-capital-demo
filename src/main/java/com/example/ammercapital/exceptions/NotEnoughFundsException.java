package com.example.ammercapital.exceptions;

public class NotEnoughFundsException extends RuntimeException{
    public NotEnoughFundsException(String message) {
        super(message);
    }
}
