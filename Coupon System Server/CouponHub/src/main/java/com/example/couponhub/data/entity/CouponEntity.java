package com.example.couponhub.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.JdbcType;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class CouponEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @JdbcType(CharJdbcType.class)
    private UUID uuid;
    @JdbcType(CharJdbcType.class)
    private UUID companyUuid;
    private int category;
    private String title;
    private LocalDate startDate;
    private LocalDate endDate;
    private int amount;
    private String description;
    private double price;
    private String image;
    @ElementCollection
    @CollectionTable(name = "coupon_purched_by", joinColumns = @JoinColumn(name = "coupon_id"))
    @JdbcType(CharJdbcType.class)
    private Set<UUID> customers = new HashSet<>();
    @Version
    private Long version;
}
