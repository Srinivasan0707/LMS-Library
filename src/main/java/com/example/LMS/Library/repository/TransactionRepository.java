package com.example.LMS.Library.repository;

import com.example.LMS.Library.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    // Find all transaction history for a specific user
    List<Transaction> findByUserId(Long userId);
}