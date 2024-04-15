package com.example.customerconnect.controller;

import com.example.customerconnect.data.dto.CustomerDto;
import com.example.customerconnect.data.dto.UserDto;
import com.example.customerconnect.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService service;
    private final RestTemplate restTemplate;

    @PostMapping("/customers/new-customer")
    public void newCustomer(@RequestBody CustomerDto dto, @RequestHeader("Authorization") String token) {
        UserDto userDto = getUser(token);
        service.createUser(dto, userDto);
    }

    @GetMapping("/customers/details")
    public ResponseEntity<CustomerDto> getCustomerDetails(@RequestHeader("Authorization") String token) {
        UserDto userDto = getUser(token);
        CustomerDto customerDetails = service.getCustomerDetails(userDto);
        return ResponseEntity.ok(customerDetails);
    }

    @GetMapping("/customers/all")
    public ResponseEntity<List<CustomerDto>> getAllCustomers() {
        return ResponseEntity.ok(service.getAllCustomers());
    }

    @PutMapping("/customers/update/{firstName}/{lastName}")
    public ResponseEntity<CustomerDto> updateCustomerDetails(@RequestHeader("Authorization") String token,
                                                             @PathVariable String firstName,
                                                             @PathVariable String lastName) {
        UserDto userDto = getUser(token);
        return ResponseEntity.ok(service.updateCustomerDetails(userDto, firstName, lastName));
    }

    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("customers/delete")
    public void deleteCustomer(@RequestHeader("Authorization") String token) {
        UserDto userDto = getUser(token);
        service.deleteCustomer(userDto);
    }

    private UserDto getUser(String token) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.set("Authorization", token);
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.exchange(
                "http://localhost:1337/parse-token",
                HttpMethod.GET,
                new HttpEntity<>(httpHeaders),
                UserDto.class);

        return userDtoResponseEntity.getBody();
    }
}
