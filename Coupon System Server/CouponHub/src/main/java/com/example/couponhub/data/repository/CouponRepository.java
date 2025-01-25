package com.example.couponhub.data.repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.couponhub.data.entity.CouponEntity;

public interface CouponRepository extends JpaRepository<CouponEntity, Long> {
  Optional<CouponEntity> findByUuid(UUID uuid);

  Set<CouponEntity> findCouponEntitiesByCustomersContaining(UUID uuid);

  Set<CouponEntity> findByCustomersNotContaining(UUID customerUuid);

  List<CouponEntity> findByCompanyUuid(UUID companyUuid);

}
