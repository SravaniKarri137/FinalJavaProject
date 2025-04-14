package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private double price;
    private String category; // e.g., "Men", "Women", "Kids", "Accessories"
    private String sportType; // e.g., "Running", "Yoga", "Basketball"
    private String brand;
    private String size;
    private String color;
    private String imageUrl;
    private int stock;
    private double rating; // Avg rating
    private int numberOfReviews;
}
