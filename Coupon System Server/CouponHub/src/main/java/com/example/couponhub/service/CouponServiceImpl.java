package com.example.couponhub.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.couponhub.data.dto.CouponDto;
import com.example.couponhub.data.dto.UserDto;
import com.example.couponhub.data.entity.CouponEntity;
import com.example.couponhub.data.repository.CouponRepository;
import com.example.couponhub.mapper.CouponMapper;
import com.example.couponhub.service.ex.CouponExpiredException;
import com.example.couponhub.service.ex.CouponNotFoundException;
import com.example.couponhub.service.ex.CouponOutOfStockException;
import com.example.couponhub.service.ex.DuplicateCouponPurchaseException;
import com.example.couponhub.service.ex.NoSuchCouponException;
import com.example.couponhub.service.ex.NoSuchCustomerException;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CouponServiceImpl implements CouponService {
  private final CouponRepository couponRepository;
  private final CouponMapper couponMapper;

  @Override
  public CouponDto getCouponByUuid(UUID couponUuid) {
    Optional<CouponEntity> couponOpt = couponRepository.findByUuid(couponUuid);
    if (couponOpt.isEmpty()) {
      throw new CouponNotFoundException("coupon not found!! ");
    }
    CouponEntity couponEntity = couponOpt.get();
    return couponMapper.map(couponEntity);
  }

  @Override
  public List<CouponDto> getAllCustomersCoupon(UserDto userDto) {
    return couponRepository.findCouponEntitiesByCustomersContaining(userDto.getUuid())
        .stream()
        .map(couponMapper::map)
        .collect(Collectors
            .toList());
  }

  @Override
  public List<CouponDto> getAllCompanyCoupon(UserDto userDto) {
    return couponRepository.findByCompanyUuid(userDto.getUuid())
        .stream()
        .map(couponMapper::map)
        .toList();
  }

  @Override
  @Transactional
  public CouponDto buyCoupon(UserDto userDto, UUID couponUuid, String token) {

    if (userDto.getUuid() == null) {
      throw new NoSuchCustomerException("No such customer ! ");

    }
    UUID uuid = userDto.getUuid();

    Optional<CouponEntity> couponOpt = couponRepository.findByUuid(couponUuid);
    if (couponOpt.isEmpty()) {
      throw new NoSuchCouponException("No such coupon ! ");
    }

    CouponEntity couponEntity = couponOpt.get();
    Set<UUID> customers = couponEntity.getCustomers();

    if (couponEntity.getAmount() <= 0) {
      throw new CouponOutOfStockException("Coupon with UUID " + couponUuid + " is out of stock.");
    }

    if (customers.contains(userDto.getUuid())) {
      throw new DuplicateCouponPurchaseException("The customer has already purchased this coupon before");
    }

    if (couponEntity.getEndDate().isBefore(LocalDate.now())) {
      throw new CouponExpiredException("This coupon has expired");
    }

    customers.add(uuid);
    couponEntity.setAmount(couponEntity.getAmount() - 1);
    couponRepository.save(couponEntity);
    return couponMapper.map(couponEntity);
  }

  @Override
  public List<CouponDto> getAllCoupons(UserDto userDto) {
    return couponRepository.findByCustomersNotContaining(userDto.getUuid())
        .stream()
        .map(couponMapper::map)
        .toList();
  }
}
