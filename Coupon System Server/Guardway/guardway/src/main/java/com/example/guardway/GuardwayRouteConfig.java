package com.example.guardway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

@Configuration

public class GuardwayRouteConfig {
  @Bean
  public CorsWebFilter corsFilter() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.addAllowedOrigin("*");
    corsConfig.addAllowedMethod("*");
    corsConfig.addAllowedHeader("*");
    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration("/**", corsConfig);
    return new CorsWebFilter(source);
  }

  @Bean
  public RouteLocator authForgeRoutes(RouteLocatorBuilder routeLocatorBuilder) {
    return routeLocatorBuilder.routes()
        // auth-forge
        .route("registerRoute", r -> r.path("/register")
            .filters(f -> f.rewritePath("/register", "/sign-up"))
            .uri("lb://auth-forge"))
        .route("loginRoute", r -> r.path("/sign-in")
            .filters(f -> f.rewritePath("/sign-in", "/login"))
            .uri("lb://auth-forge"))
        .route("validateTokenRoute", r -> r.path("/validateToken")
            .filters(f -> f.rewritePath("/validateToken", "/parse-token"))
            .uri("lb://auth-forge"))
        .route("editDetailsRoute", r -> r.path("/edit-details")
            .filters(f -> f.rewritePath("/edit-details", "/update-details"))
            .uri("lb://auth-forge"))

        // customer-connect
        .route("addCustomerRoute", r -> r.path("/add-customer")
            .filters(f -> f.rewritePath("/add-customer", "/customers"))
            .uri("lb://customer-connect"))
        .route("findCustomerDetailsRoute", r -> r.path("/get-customer-details")
            .filters(f -> f.rewritePath("/get-customer-details", "/customers/details"))
            .uri("lb://customer-connect"))
        .route("findAllCustomersRoute", r -> r.path("/show-all")
            .filters(f -> f.rewritePath("/show-all", "/customers"))
            .uri("lb://customer-connect"))
        .route("updateCustomersRoute", r -> r.path("/update-customer")
            .filters(f -> f.rewritePath("/update-customer", "/customers"))
            .uri("lb://customer-connect"))
        .route("deleteCustomerRoute", r -> r.path("/delete-customer")
            .filters(f -> f.rewritePath("/delete-customer", "/customers"))
            .uri("lb://customer-connect"))

        // coupon-hub
        .route("findCouponRoute", r -> r.path("/get-coupon/{couponUuid}")
            .filters(f -> f.rewritePath("/get-coupon/(?<couponUuid>.*)", "/coupons/${couponUuid}"))
            .uri("lb://coupon-hub"))
        .route("findAllCustomerCouponRoute", r -> r.path("/get-all-customer-coupons")
            .filters(f -> f.rewritePath("/get-all-customer-coupons", "/coupons/customer"))
            .uri("lb://coupon-hub"))
        .route("findAllCompanyCouponsRoute", r -> r.path("/get-all-company-coupons/{companyUuid}")
            .filters(
                f -> f.rewritePath("/get-all-company-coupons/(?<companyUuid>.*)", "/coupons/company/${companyUuid}"))
            .uri("lb://coupon-hub"))
        .route("buyCouponRoute", r -> r.path("/buy/{couponUuid}")
            .filters(f -> f.rewritePath("/buy/(?<couponUuid>.*)", "/coupons/purchase/${couponUuid}"))
            .uri("lb://coupon-hub"))
        .route("allCouponRoute", r -> r.path("/all-coupons")
            .filters(f -> f.rewritePath("/all-coupons", "/coupons/all"))
            .uri("lb://coupon-hub"))
        .build();
  }

}
