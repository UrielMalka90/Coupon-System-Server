package com.example.couponhub.data.repository;

import com.example.couponhub.data.entity.CouponEntity;
import io.micrometer.common.KeyValues;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

public interface CouponRepository extends JpaRepository<CouponEntity,Long> {
    Optional<CouponEntity> findByUuid(UUID uuid);
    Set<CouponEntity> findCouponEntitiesByCustomersContaining(UUID uuid);

    List<CouponEntity> findByCompanyUuid(UUID companyUuid);

}
