package com.example.entity;

import java.util.List;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private String role = "USER"; // Default to USER
    private String email;

    // One-to-Many relationship with TransactionDetail
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TransactionDetail> transactions;

    // === Getters & Setters ===
   


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<TransactionDetail> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionDetail> transactions) {
        this.transactions = transactions;
    }

    // Method to check if the user is an admin
    public boolean isAdmin() {
        return "ADMIN".equalsIgnoreCase(this.role);
    }

	public User orElseThrow(Object object) {
		// TODO Auto-generated method stub
		return null;
	}
}
