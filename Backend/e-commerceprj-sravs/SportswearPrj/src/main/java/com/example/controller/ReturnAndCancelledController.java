package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.ReturnAndCancelled;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.security.JwtUtil;
import com.example.service.ReturnAndCancelledService;

import jakarta.servlet.http.HttpServletRequest;
@RestController
@RequestMapping("/api/returns")
public class ReturnAndCancelledController {

    @Autowired
    private ReturnAndCancelledService returnService;

    @Autowired
    private UserRepository userRepository;
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/all")
    public ResponseEntity<?> getAllReturnsForAdmin() {
        // This could include a check to verify if the user is an admin
        

        List<ReturnAndCancelled> allReturns = returnService.getAllReturnAndCancelledItems();

        return ResponseEntity.ok(allReturns);
    }

}
