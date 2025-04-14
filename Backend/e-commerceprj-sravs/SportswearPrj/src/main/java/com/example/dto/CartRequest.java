package com.example.dto;

import lombok.Data;

@Data
public class CartRequest {
    private Long productId; // Product ID to be added to the cart
    private int quantity; // Quantity of the product to be added
}
