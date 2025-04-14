package com.example.repository;

import com.example.entity.Review;
import com.example.entity.Product;
import com.example.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByProduct(Product product);
    boolean existsByUserAndProduct(User user, Product product);
    Optional<Review> findById(Long id);
}
