package com.example.couponhub.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.couponhub.data.dto.CouponDto;
import com.example.couponhub.data.dto.UserDto;
import com.example.couponhub.service.CouponService;
import com.example.couponhub.service.ex.NoSuchUserException;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
public class CouponController {
  private final CouponService service;
  private final RestTemplate restTemplate;

  @GetMapping("/coupons/{couponUuid}")
  public ResponseEntity<CouponDto> getCouponByUuid(@RequestHeader("Authorization") String token,
      @PathVariable UUID couponUuid) {
    getUser(token);
    return ResponseEntity.ok(service.getCouponByUuid(couponUuid));
  }

  @GetMapping("/coupons/customer")
  public ResponseEntity<List<CouponDto>> getAllCustomersCoupon(@RequestHeader("Authorization") String token) {
    UserDto userDto = getUser(token);
    return ResponseEntity.ok(service.getAllCustomersCoupon(userDto));
  }

  @GetMapping("/coupons/company")
  public ResponseEntity<List<CouponDto>> getAllCompanyCoupon(@RequestHeader("Authorization") String token) {
    UserDto userDto = getUser(token);
    return ResponseEntity.ok(service.getAllCompanyCoupon(userDto));
  }

  @PostMapping("/coupons/purchase/{couponUuid}")
  public ResponseEntity<CouponDto> buyCoupon(@RequestHeader("Authorization") String token,
      @PathVariable UUID couponUuid) {
    UserDto userDto = getUser(token);
    return ResponseEntity.ok(service.buyCoupon(userDto, couponUuid, token));
  }

  public UserDto getUser(String token) {
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.set("Authorization", token);
    httpHeaders.setContentType(MediaType.APPLICATION_JSON);
    ResponseEntity<UserDto> userDtoResponseEntity = restTemplate.exchange(
        "http://localhost:1337/parse-token",
        HttpMethod.GET,
        new HttpEntity<>(httpHeaders),
        UserDto.class);
    if (userDtoResponseEntity.getBody() == null) {
      throw new NoSuchUserException("user not found! ");
    }

    return userDtoResponseEntity.getBody();
  }

  @GetMapping("/coupons/all")
  public List<CouponDto> getAllCoupons(@RequestHeader("Authorization") String token) {
    UserDto userDto = getUser(token);
    return service.getAllCoupons(userDto);
  }
}
