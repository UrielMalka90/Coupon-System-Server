package com.example.customerconnect.service;

import com.example.customerconnect.data.dto.CustomerDto;
import com.example.customerconnect.data.dto.UserDto;

import java.util.List;

public interface CustomerService {
    void createUser(CustomerDto newCustomer, UserDto dto);

    CustomerDto getCustomerDetails(UserDto userDto);

    List<CustomerDto> getAllCustomers();

    CustomerDto updateCustomerDetails(UserDto userDto,String firstName,String lastName);

    void deleteCustomer(UserDto userDto);
}
