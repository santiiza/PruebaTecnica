package com.development.account.domain.dto.Report;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class AccountReportDto {
    String accountNumber;
    String accountType;
    BigDecimal balance;
    List<TransactionReportDto> transactions;
}
