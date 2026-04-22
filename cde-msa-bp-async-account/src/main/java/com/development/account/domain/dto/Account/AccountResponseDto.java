package com.development.account.domain.dto.Account;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountResponseDto {
    String accountNumber;
    String clientId;
    String accountType;
    BigDecimal initialBalance;
    Boolean status;
}
