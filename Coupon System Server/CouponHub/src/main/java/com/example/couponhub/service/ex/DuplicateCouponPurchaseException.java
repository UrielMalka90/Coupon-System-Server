package com.example.couponhub.service.ex;

public class DuplicateCouponPurchaseException extends RuntimeException {
    public DuplicateCouponPurchaseException(String s) {
        super(s);
    }
}
