package com.example.couponhub.mapper;

import com.example.couponhub.data.dto.CouponDto;
import com.example.couponhub.data.entity.CouponEntity;

public interface CouponMapper {
    CouponDto map(CouponEntity entity);
    CouponEntity map(CouponDto dto);
}
