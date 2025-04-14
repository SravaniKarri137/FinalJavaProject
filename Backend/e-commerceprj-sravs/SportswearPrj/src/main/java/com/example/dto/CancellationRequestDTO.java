package com.example.dto;

import java.time.LocalDateTime;

public class CancellationRequestDTO {
    private Long itemId;
    private Long productId;
    private Long userId;
    private String reason;
    private String status;
    private LocalDateTime requestedAt;

    public CancellationRequestDTO(Long itemId, Long productId, Long userId, String reason, String status, LocalDateTime requestedAt) {
        this.itemId = itemId;
        this.productId = productId;
        this.userId = userId;
        this.reason = reason;
        this.status = status;
        this.requestedAt = requestedAt;
    }

    // Getters and setters
}
