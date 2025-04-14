package com.example.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ReturnAndCancelled {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderItemId;
    @ManyToOne
    private Order order;

    @ManyToOne
    private User user;
    private String type; // CANCEL or RETURN
    private String remarks;

    @ElementCollection
    private List<String> imagePaths;

    private LocalDateTime createdAt;

    private boolean approved = false;
    private boolean rejected = false;

    private String decisionBy;
    private LocalDateTime decisionDate;
}
