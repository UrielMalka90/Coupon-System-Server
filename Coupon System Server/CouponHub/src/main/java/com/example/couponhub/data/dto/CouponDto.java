package com.example.couponhub.data.dto;

import lombok.*;

import java.time.LocalDate;
import java.util.UUID;

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
    private String image;
    private int category;
}
