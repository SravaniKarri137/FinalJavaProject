package com.example.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDetail {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String transactionType;
    private LocalDateTime transactionDate;
    private String status;

    private boolean cancellationRequested = false;
    private boolean isRefunded = false;

    private String remarks;

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

	public void setIsRefunded(boolean b) {
		// TODO Auto-generated method stub
		
	}
}
