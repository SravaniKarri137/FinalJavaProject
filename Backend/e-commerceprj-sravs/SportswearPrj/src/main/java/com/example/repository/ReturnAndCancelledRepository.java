package com.example.repository;

import com.example.dto.CancellationRequestDTO;
import com.example.entity.ReturnAndCancelled;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReturnAndCancelledRepository extends JpaRepository<ReturnAndCancelled, Long> {
    List<ReturnAndCancelled> findByApprovedFalseAndRejectedFalse();

    @Query("SELECT r FROM ReturnAndCancelled r WHERE r.user.id = :userId AND r.order.id = :orderId")
    List<ReturnAndCancelled> findByUserIdAndOrderId(@Param("userId") Long userId, @Param("orderId") Long orderId);
}

