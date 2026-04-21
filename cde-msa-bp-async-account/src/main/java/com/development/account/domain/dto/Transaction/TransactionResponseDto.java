package com.development.account.domain.dto.Transaction;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponseDto {
    Long id;
    String accountNumber;
    LocalDateTime transactionDate;
    String transactionType;
    BigDecimal amount;
    BigDecimal balance;
}
