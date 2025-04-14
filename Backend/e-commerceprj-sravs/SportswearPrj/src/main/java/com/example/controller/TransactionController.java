package com.example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.web.bind.annotation.*;

import com.example.entity.TransactionDetail;
import com.example.service.TransactionService;

@RestController
@RequestMapping("/api/transactions")
@CrossOrigin(origins = "*")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    // Endpoint for a user to check their own transactions. Username is taken from the token.
    @GetMapping("/user")
    public ResponseEntity<List<TransactionDetail>> getUserTransactions() {
        // Extract the username from the JWT token
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();
        List<TransactionDetail> userTransactions = transactionService.getTransactionsByUsername(username);
        return ResponseEntity.ok(userTransactions);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<TransactionDetail>> getAllTransactions(
            @RequestParam(required = false) String transactionType) {
        List<TransactionDetail> transactions = transactionService.getAllTransactions(transactionType);
        return ResponseEntity.ok(transactions);
    }
}
