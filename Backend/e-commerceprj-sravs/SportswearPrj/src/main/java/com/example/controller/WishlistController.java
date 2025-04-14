package com.example.controller;

import com.example.entity.WishlistItem;
import com.example.repository.UserRepository;
import com.example.service.WishlistService;
import com.example.security.JwtUtil;
import com.example.entity.User;
import jakarta.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/wishlist")
public class WishlistController {

    @Autowired
    private WishlistService wishlistService;

    @Autowired
    private JwtUtil jwtUtil;

    // Add product to the wishlist
    @PostMapping("/add")
    public ResponseEntity<?> addProductToWishlist(@RequestParam Long productId, HttpServletRequest request) {
        String username = getUsernameFromJwt(request);
        
        // Assuming you have a userRepository to fetch the user by username
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        WishlistItem wishlistItem = wishlistService.addProductToWishlist(user.getId(), productId);
        if (wishlistItem == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product or User not found");
        }
        return ResponseEntity.ok(wishlistItem);
    }

    // Remove product from the wishlist
    @DeleteMapping("/remove")
    public ResponseEntity<String> removeProductFromWishlist(@RequestParam Long productId, HttpServletRequest request) {
        String username = getUsernameFromJwt(request);
        
        // Assuming you have a userRepository to fetch the user by username
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User not found");
        }

        String result = wishlistService.removeProductFromWishlist(user.getId(), productId);
        return ResponseEntity.ok(result);
    }

    // Get all products in a user's wishlist
    @GetMapping("/user")
    public ResponseEntity<List<WishlistItem>> getUserWishlist(HttpServletRequest request) {
        String username = getUsernameFromJwt(request);

        // Assuming you have a userRepository to fetch the user by username
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }

        List<WishlistItem> wishlistItems = wishlistService.getWishlistByUserId(user.getId());
        if (wishlistItems == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(wishlistItems);
    }

    // Helper method to extract username from JWT token in request header
    private String getUsernameFromJwt(HttpServletRequest request) {
        String token = request.getHeader("Authorization");
        if (token != null && token.startsWith("Bearer ")) {
            token = token.substring(7); // Remove "Bearer " prefix
            return jwtUtil.extractUsername(token);
        }
        throw new RuntimeException("JWT token is missing or invalid");
    }
}
