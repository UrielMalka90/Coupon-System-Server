package com.example.couponhub.service;

import java.util.List;
import java.util.UUID;

import com.example.couponhub.data.dto.CouponDto;
import com.example.couponhub.data.dto.UserDto;

public interface CouponService {

    CouponDto getCouponByUuid(UUID couponUuid);

    List<CouponDto> getAllCustomersCoupon(UserDto userDto);
    List<CouponDto> getAllCompanyCoupon(UserDto userDto);
    CouponDto buyCoupon(UserDto userDto, UUID couponUuid, String token);

    List<CouponDto> getAllCoupons(UserDto userDto);


}

    

