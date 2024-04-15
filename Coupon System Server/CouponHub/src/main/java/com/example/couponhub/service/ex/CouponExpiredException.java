package com.example.couponhub.service.ex;

public class CouponExpiredException extends RuntimeException {
    public CouponExpiredException(String s) {
        super(s);
    }
}
