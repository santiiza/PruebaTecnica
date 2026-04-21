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
public class AccountPatchRequestDto {
    @Pattern(regexp = "^[0-9]+$", message = "Caracteres inválidos")
    @Size(max = 20, message = "debe tener máximo {max} caracteres.")
    String accountNumber;
    @Pattern(regexp = "^[A-Za-z ]+$", message = "Solo se permiten letras y espacios")
    @Size(max = 80, message = "debe tener máximo {max} caracteres.")
    String customer;
    @Pattern(regexp = "^[a-zA-Z]+$", message = "Carácteres inválidos")
    @Size(max = 20, message = "debe tener máximo {max} caracteres.")
    String accountType;
    @PositiveOrZero
    @Digits(integer = 15, fraction = 2, message = "Máximo 15 dígitos enteros y 2 decimales")
    BigDecimal initialBalance;
    Boolean status;
}
