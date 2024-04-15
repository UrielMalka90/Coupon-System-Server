package com.example.couponhub.mapper;

import com.example.couponhub.data.dto.CouponDto;
import com.example.couponhub.data.entity.CouponEntity;
import org.springframework.stereotype.Component;

@Component
public class CouponMapperImpl implements CouponMapper {
    @Override
    public CouponDto map(CouponEntity entity) {
        return CouponDto.builder()
                .uuid(entity.getUuid())
                .companyUuid(entity.getCompanyUuid())
                .title(entity.getTitle())
                .description(entity.getDescription())
                .startDate(entity.getStartDate())
                .endDate(entity.getEndDate())
                .price(entity.getPrice())
                .image(entity.getImage())
                .category(entity.getCategory())
                .build();
    }

    @Override
    public CouponEntity map(CouponDto dto) {
        return CouponEntity.builder()
                .uuid(dto.getUuid())
                .companyUuid(dto.getCompanyUuid())
                .title(dto.getTitle())
                .description(dto.getDescription())
                .startDate(dto.getStartDate())
                .endDate(dto.getEndDate())
                .price(dto.getPrice())
                .image(dto.getImage())
                .category(dto.getCategory())
                .build();
    }
}
