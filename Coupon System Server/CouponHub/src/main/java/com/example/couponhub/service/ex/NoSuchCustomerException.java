package com.example.couponhub.service.ex;

public class NoSuchCustomerException extends RuntimeException {
    public NoSuchCustomerException(String s) {
        super(s);
    }
}
