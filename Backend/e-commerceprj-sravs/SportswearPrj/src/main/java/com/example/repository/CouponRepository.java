package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.example.entity.Coupon;

public interface CouponRepository extends JpaRepository<Coupon, Long> {
    // Query method to find a coupon by its code (ignoring case)
    Coupon findByCodeIgnoreCase(String code);
}
