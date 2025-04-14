package com.example.service;

import com.example.entity.*;
import com.example.repository.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepo;

    @Autowired
    private ProductRepository productRepo;

    @Autowired
    private UserRepository userRepo;

    @Autowired
    private ProductService productService;

    // Add Review - takes the username from the controller
    public Review addReview(String username, Long productId, String comment, int rating) {
        User user = userRepo.findByUsername(username);
        Product product = productRepo.findById(productId).orElse(null);
        if (user == null || product == null) return null;

        // Prevent duplicate reviews (optional)
        if (reviewRepo.existsByUserAndProduct(user, product)) {
            throw new RuntimeException("You have already reviewed this product.");
        }

        Review review = new Review();
        review.setUser(user);
        review.setProduct(product);
        review.setComment(comment);
        review.setRating(rating);

        Review saved = reviewRepo.save(review);

        // Update average product rating
        productService.updateProductRating(productId);

        return saved;
    }

    // Get Reviews for a Product
    public List<Review> getReviewsForProduct(Long productId) {
        return productRepo.findById(productId)
                          .map(product -> reviewRepo.findByProduct(product))
                          .orElse(List.of());
    }


    // Edit Review - ensures the user can only edit their own review
    public Review editReview(Long reviewId, String username, String comment, int rating) {
        Optional<Review> reviewOptional = reviewRepo.findById(reviewId);

        if (reviewOptional.isEmpty()) {
            throw new RuntimeException("Review not found.");
        }

        Review review = reviewOptional.get();

        // Check if the review belongs to the logged-in user
        if (!review.getUser().getUsername().equals(username)) {
            throw new RuntimeException("You cannot edit someone else's review.");
        }

        // Update review's comment and rating
        review.setComment(comment);
        review.setRating(rating);

        // Save the updated review
        Review updatedReview = reviewRepo.save(review);

        // Optionally update product rating if needed
        productService.updateProductRating(review.getProduct().getId());

        return updatedReview;
    }
}
