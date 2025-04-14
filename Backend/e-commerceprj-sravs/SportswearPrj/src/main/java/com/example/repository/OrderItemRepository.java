package com.example.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.entity.Order;
import com.example.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
    

	List<Order> findAllByUsername(String username);

	List<Order> findAllByUsernameAndStatus(String username, String string);

	List<Order> findAllById(Long userId);
	@Query("SELECT o FROM Order o LEFT JOIN FETCH o.items WHERE o.user.id = :userId")
	List<Order> findOrdersForUser(@Param("userId") Long userId);

}
