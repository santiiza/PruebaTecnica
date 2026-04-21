package com.development.account.application.input.port;

import com.development.account.domain.dto.Transaction.TransactionRequestDto;
import com.development.account.domain.dto.Transaction.TransactionResponseDto;

public interface TransactionInputPort {
    TransactionResponseDto createTransaction(TransactionRequestDto request);
    TransactionResponseDto getTransactionById(Long id);
    TransactionResponseDto updateTransaction(Long id, TransactionRequestDto request);
    void deleteTransaction(Long id);
}
