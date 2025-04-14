package com.example.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String code;
    private double discountPercentage;
    private boolean active;

    private Double minPurchaseAmount;

    @Column(nullable = true)
    private LocalDateTime expiryDate; // Optional

    private Integer usageLimit; // Maximum number of times the coupon can be used per user

    @ElementCollection
    @MapKeyColumn(name = "user_id")
    @Column(name = "usage_count")
    private Map<Long, Integer> userUsageMap = new HashMap<>(); // Map to track user usage counts

    /**
     * Sets the number of times a user has used the coupon.
     * 
     * @param userId The ID of the user.
     * @param times The number of times the user has used the coupon.
     */
    public void setTimesUsed(Long userId, int times) {
        userUsageMap.put(userId, times);
    }

    /**
     * Checks if the coupon can be used by a user.
     * 
     * @param userId The ID of the user.
     * @return true if the coupon can be used, false otherwise.
     */
    public boolean canBeUsedByUser(Long userId) {
        // Check if the coupon is active
        if (!active) {
            return false;
        }

        // Check if the coupon has expired
        if (expiryDate != null && LocalDateTime.now().isAfter(expiryDate)) {
            return false;
        }

        // Check if the user has exceeded the usage limit
        int usageCount = userUsageMap.getOrDefault(userId, 0);
        if (usageLimit != null && usageCount >= usageLimit) {
            return false;
        }

        // Check if the user is trying to use the coupon with a minimum purchase amount requirement
        // Add logic here if there's a minimum purchase requirement

        return true;
    }

    /**
     * Increments the usage count of the coupon for a specific user.
     * 
     * @param userId The ID of the user.
     */
    public void incrementUsage(Long userId) {
        int usageCount = userUsageMap.getOrDefault(userId, 0);
        userUsageMap.put(userId, usageCount + 1);
    }
}
