package com.development.account.domain.dto.Report;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class TransactionReportDto {
    LocalDateTime transactionDate;
    BigDecimal amount;
    String transactionType;
    BigDecimal balance;
}
