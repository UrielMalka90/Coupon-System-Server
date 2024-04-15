package com.example.customerconnect.service.ex;

public class CustomerAlreadyExistException extends RuntimeException {
    public CustomerAlreadyExistException(String s) {
        super(s);
    }
}
