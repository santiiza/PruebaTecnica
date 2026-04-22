package com.development.account.domain.dto.Account;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountRequestDto {
    @NotBlank(message = "Account number must not be empty.")
    @Pattern(regexp = "^[0-9]+$", message = "Account number must match pattern {regexp}")
    @Size(max = 20, message = "Account number must be maximum {max} characters")
    String accountNumber;
    @NotBlank(message = "Client Id must not be empty.")
    @Pattern(regexp = "^[0-9A-Za-z]+$", message = "Client Id must match pattern {regexp}")
    @Size(max = 30, message = "Client Id must be maximum {max} characters")
    String clientId;
    @NotBlank(message = "Account type must not be empty.")
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Account type must match pattern {regexp}")
    @Size(max = 20, message = "Account type must be maximum {max} characters")
    String accountType;
    @PositiveOrZero
    @Digits(integer = 15, fraction = 2, message = "Initial Balance, maximum 15 integer digits and 2 decimal places.")
    BigDecimal initialBalance;
    @NotNull(message = "Status must not be null.")
    Boolean status;
}
