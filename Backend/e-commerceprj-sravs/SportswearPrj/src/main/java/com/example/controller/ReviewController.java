package com.example.controller;

import com.example.dto.ReviewRequest;
import com.example.entity.Review;
import com.example.service.ReviewService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reviews")
@CrossOrigin(origins = "*")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    // Add Review - take the username from the token
    @PostMapping("/add")
    public Review addReview(@RequestBody ReviewRequest request) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return reviewService.addReview(username, request.getProductId(), request.getComment(), request.getRating());
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<List<Review>> getProductReviews(@PathVariable Long productId) {
        List<Review> reviews = reviewService.getReviewsForProduct(productId);
        if (reviews.isEmpty()) {
            return ResponseEntity.notFound().build(); // 404 if product doesn't exist
        }
        return ResponseEntity.ok(reviews); // 200 with list
    }


    // Edit Review - take the username from the token
    @PutMapping("/edit/{reviewId}")
    public Review editReview(@PathVariable Long reviewId, @RequestBody ReviewRequest request) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        return reviewService.editReview(reviewId, username, request.getComment(), request.getRating());
    }
}
