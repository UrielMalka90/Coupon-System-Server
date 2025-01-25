package com.example.couponhub.data.dto;

import java.time.LocalDate;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class CouponDto {
    private UUID uuid;
    private UUID companyUuid;
    private String title;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private double price;
    private int amount;
    private String image;
    private int category;
}
