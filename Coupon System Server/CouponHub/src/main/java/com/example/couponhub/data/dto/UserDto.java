package com.example.couponhub.data.dto;

import lombok.Data;

import java.util.UUID;

@Data
public class UserDto {

    private final String username;
    private final UUID uuid;
}
