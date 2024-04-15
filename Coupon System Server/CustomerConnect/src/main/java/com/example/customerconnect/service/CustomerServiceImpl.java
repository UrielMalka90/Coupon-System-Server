package com.example.customerconnect.service;

import com.example.customerconnect.data.dto.CustomerDto;
import com.example.customerconnect.data.dto.UserDto;
import com.example.customerconnect.data.entity.CustomerEntity;
import com.example.customerconnect.data.repository.CustomerRepository;
import com.example.customerconnect.mapper.CustomerMapper;
import com.example.customerconnect.service.ex.CustomerAlreadyExistException;
import com.example.customerconnect.service.ex.CustomerNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
@Service
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Override
    public void createUser(CustomerDto newCustomer, UserDto dto) {
        UUID useruuid = dto.getUuid();
        Optional<CustomerEntity> customerOpt = customerRepository.findByUuid(useruuid);

        if (customerOpt.isPresent()) {
            throw new CustomerAlreadyExistException("customer already exist !! ");
        }
        newCustomer.setUuid(useruuid);
        CustomerEntity customerEntity = customerMapper.map(newCustomer);
        customerRepository.save(customerEntity);
    }

    @Override
    public CustomerDto getCustomerDetails(UserDto userDto) {
        UUID useruuid = userDto.getUuid();
        Optional<CustomerEntity> customerOpt = customerRepository.findByUuid(useruuid);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("customer not found!! ");
        }
        CustomerEntity customerEntity = customerOpt.get();
        return customerMapper.map(customerEntity);
    }

    @Override
    public List<CustomerDto> getAllCustomers() {
        List<CustomerEntity> entityList = customerRepository.findAll();
        List<CustomerDto> dtoList = new ArrayList<>();
        for (CustomerEntity customerEntity : entityList) {
           dtoList.add(customerMapper.map(customerEntity));
        }
        return dtoList;
    }

    @Override
    public CustomerDto updateCustomerDetails(UserDto userDto,String firstName,String lastName) {
        UUID useruuid = userDto.getUuid();
        Optional<CustomerEntity> customerOpt = customerRepository.findByUuid(useruuid);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("customer not found!! ");
        }
        CustomerEntity customerEntity = customerOpt.get();
        customerEntity.setFirstName(firstName);
        customerEntity.setLastName(lastName);
        CustomerEntity save = customerRepository.save(customerEntity);
        return customerMapper.map(save);
    }

    @Override
    public void deleteCustomer(UserDto userDto) {
        UUID useruuid = userDto.getUuid();
        Optional<CustomerEntity> customerOpt = customerRepository.findByUuid(useruuid);

        if (customerOpt.isEmpty()) {
            throw new CustomerNotFoundException("customer not found!! ");
        }
        CustomerEntity customerEntity = customerOpt.get();
        customerRepository.delete(customerEntity);
    }
}
