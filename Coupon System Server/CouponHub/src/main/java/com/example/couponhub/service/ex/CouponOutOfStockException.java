package com.example.couponhub.service.ex;

public class CouponOutOfStockException extends RuntimeException {
    public CouponOutOfStockException(String s) {
        super(s);
    }
}
