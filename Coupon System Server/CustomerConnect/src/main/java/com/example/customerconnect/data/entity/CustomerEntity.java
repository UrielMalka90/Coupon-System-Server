package com.example.customerconnect.data.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.JdbcType;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.descriptor.jdbc.CharJdbcType;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder

public class CustomerEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @JdbcType(CharJdbcType.class)
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String email;
    @CreationTimestamp
    private Timestamp creationTimestamp;
    @UpdateTimestamp
    private Timestamp updateTimestamp;
    @ElementCollection
    @CollectionTable(name =  "coupon_id", joinColumns = @JoinColumn(name ="coupon_purched_by"))
    private List<Integer> coupons = new ArrayList<>();
    @Version
    private Long version;
}
