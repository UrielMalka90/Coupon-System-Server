package com.example.customerconnect.mapper;

import com.example.customerconnect.data.dto.CustomerDto;
import com.example.customerconnect.data.entity.CustomerEntity;
import org.springframework.stereotype.Component;

@Component
public class CustomerMapperImpl implements CustomerMapper {
    @Override
    public CustomerDto map(CustomerEntity entity) {
        return CustomerDto.builder()
                .uuid(entity.getUuid())
                .email(entity.getEmail())
                .firstName(entity.getFirstName())
                .lastName(entity.getLastName())
                .build();
    }

    @Override
    public CustomerEntity map(CustomerDto dto) {
        return CustomerEntity.builder()
                .uuid(dto.getUuid())
                .email(dto.getEmail())
                .firstName(dto.getFirstName())
                .lastName(dto.getLastName())
                .build();
    }
}
