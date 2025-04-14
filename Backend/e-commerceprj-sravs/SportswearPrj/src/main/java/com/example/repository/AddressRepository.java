package com.example.repository;

import com.example.entity.Address;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {
	Optional<Address> findById(Long id);

    // You can add custom queries here if needed, for now JpaRepository provides all the basic ones
}
