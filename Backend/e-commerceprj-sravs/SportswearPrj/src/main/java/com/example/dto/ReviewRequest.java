package com.example.dto;

import lombok.Data;

@Data
public class ReviewRequest {

    private Long productId;
    private String comment;
    private int rating;
}
