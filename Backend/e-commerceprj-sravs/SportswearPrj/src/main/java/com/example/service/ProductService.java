package com.example.service;

import com.example.entity.Product;
import com.example.entity.Review;
import com.example.repository.ProductRepository;
import com.example.repository.ReviewRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository repo;

    @Autowired
    private ReviewRepository reviewRepo;

    public List<Product> getAllProducts() {
        return repo.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> productOptional = repo.findById(productId);
        return productOptional.orElse(null);
    }

    public Product saveProduct(Product product) {
        return repo.save(product);
    }

    public void deleteProduct(Long productId) {
        repo.deleteById(productId);
    }

    public List<Product> getByCategory(String category) {
        return repo.findByCategory(category);
    }

    public List<Product> getByBrand(String brand) {
        return repo.findByBrand(brand);
    }

    // ✅ Called after a new review is added
    public void updateProductRating(Long productId) {
        Product product = repo.findById(productId).orElse(null);
        if (product != null) {
            List<Review> reviews = reviewRepo.findByProduct(product);
            double avgRating = reviews.stream().mapToInt(Review::getRating).average().orElse(0.0);
            product.setRating(avgRating);
            repo.save(product);
        }
    }
}
