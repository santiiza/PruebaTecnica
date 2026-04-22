package com.development.account.application.output.port;

import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import com.development.account.infraestructure.output.repository.entity.Transaction;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface TransactionAdapterPort {
    TransactionResponseDto save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    List<Transaction> findByAccountNumberAndTransactionDateBetween(String accountNumber, LocalDateTime startDateTime, LocalDateTime endDateTime);
    void delete(Transaction transaction);
}
