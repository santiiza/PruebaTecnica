package com.development.account.infraestructure.output.repository;

import com.development.account.infraestructure.output.repository.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepository extends JpaRepository<Transaction, Long> {
    List<Transaction> findByAccount_AccountNumberAndTransactionDateBetween(String accountNumber, LocalDateTime startDate, LocalDateTime endDate);
}
