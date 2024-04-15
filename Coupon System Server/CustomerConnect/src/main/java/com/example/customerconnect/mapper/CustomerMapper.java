package com.example.customerconnect.mapper;

import com.example.customerconnect.data.dto.CustomerDto;
import com.example.customerconnect.data.entity.CustomerEntity;

public interface CustomerMapper {
    CustomerDto map(CustomerEntity entity);
    CustomerEntity map(CustomerDto dto);
}
