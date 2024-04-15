package com.example.guardway;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration

public class GuardwayRouteConfig {
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("customer-details", r -> r.path("/api/customers/details")
                        .filters(f -> f.rewritePath("/api/customers/details", "/api/customer/details"))
                        .uri("lb://customer-service")
                )
                .route("customer-update", r -> r.path("/api/customers/updateDetails/**")
                        .filters(f -> f.rewritePath("/api/customers/updateDetails/(?<firstName>.*)/(?<lastName>.*)", "/api/customer/updateDetails/${firstName}/${lastName}"))
                        .uri("lb://customer-service")
                )
                .route("customer-delete", r -> r.path("/api/customers/delete/**")
                        .filters(f -> f.rewritePath("/api/customers/delete/(?<uuid>.*)", "/api/customer/delete/${uuid}"))
                        .uri("lb://customer-service")
                )
                .route("coupon-details", r -> r.path("/api/coupons/{couponUuid}")
                        .uri("lb://coupon-service")
                )
                .route("customer-coupons", r -> r.path("/api/coupons/customer")
                        .uri("lb://coupon-service")
                )
                .route("company-coupons", r -> r.path("/api/coupons/company")
                        .uri("lb://coupon-service")
                )
                .route("purchase-coupon", r -> r.path("/api/coupons/purchase/**")
                        .filters(f -> f.rewritePath("/api/coupons/purchase/(?<couponUuid>.*)", "/api/coupons/purchase/${couponUuid}"))
                        .uri("lb://coupon-service")
                )
                .route("delete-customer", r -> r.path("/api/coupons/customers/delete/**")
                        .filters(f -> f.rewritePath("/api/coupons/customers/delete/(?<uuid>.*)", "/api/coupons/customers/delete/${uuid}"))
                        .uri("lb://coupon-service")
                )
                .build();
    }
}








