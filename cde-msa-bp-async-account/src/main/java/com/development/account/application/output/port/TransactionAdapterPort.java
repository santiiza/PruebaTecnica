package com.development.account.application.output.port;

import com.development.account.domain.dto.Transaction.TransactionResponseDto;
import com.development.account.infraestructure.output.repository.entity.Transaction;

import java.util.Optional;

public interface TransactionAdapterPort {
    TransactionResponseDto save(Transaction transaction);
    Optional<Transaction> findById(Long id);
    void delete(Transaction transaction);
}
