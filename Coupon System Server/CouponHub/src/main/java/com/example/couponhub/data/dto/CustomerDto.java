package com.example.couponhub.data.dto;

import lombok.*;

import java.util.UUID;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CustomerDto {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
}
